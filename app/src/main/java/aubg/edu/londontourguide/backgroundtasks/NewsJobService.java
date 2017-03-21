package aubg.edu.londontourguide.backgroundtasks;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.SimpleJobService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import aubg.edu.londontourguide.data.NewsContract;

/**
 * Created by nikola on 20.03.17.
 */

public class NewsJobService extends SimpleJobService {

    private static final String LOG_TAG = NewsJobService.class.getSimpleName();

    private static final String[] NEWS_PROJECTION = new String[] {
            NewsContract.NewsEntry._ID,
            NewsContract.NewsEntry.COLUMN_TITLE,
            NewsContract.NewsEntry.COLUMN_URL
    };


    @Override
    public int onRunJob(JobParameters job) {
        Log.d(LOG_TAG, "Starting sync");

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String newsJSONString = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            final String NEWS_BASE_URL =
                    "https://www.reddit.com/r/london/new.json?sort=new";

            Uri builtUri = Uri.parse(NEWS_BASE_URL);

            URL url = new URL(builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return JobService.RESULT_FAIL_RETRY;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return JobService.RESULT_FAIL_NORETRY;
            }

            newsJSONString = buffer.toString();
            getNewsDataFromJSON(newsJSONString);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return JobService.RESULT_SUCCESS;
    }
    private void getNewsDataFromJSON(String newsJSONString) throws JSONException {

        final String NEWS_DATA = "data";
        final String NEWS_CHILDREN = "children";
        final String NEWS_CHILD_DATA = "data";
        final String NEWS_CHILD_DATA_TITLE = "title";
        final String NEWS_CHILD_DATA_URL = "url";


        try {
            JSONObject newsJSON = new JSONObject(newsJSONString);
            JSONObject newsData = newsJSON.getJSONObject(NEWS_DATA);
            JSONArray newsChildren = newsData.getJSONArray(NEWS_CHILDREN);

            int index = 0;

            Vector<ContentValues> cVVector = new Vector<ContentValues>(index);

            while(index < newsChildren.length() && cVVector.size() < 10) {

                String title;
                String url;

                JSONObject newsChildData = newsChildren.getJSONObject(index).getJSONObject(NEWS_CHILD_DATA);
                index++;

                title = newsChildData.getString(NEWS_CHILD_DATA_TITLE);
                url = newsChildData.getString(NEWS_CHILD_DATA_URL);

                if(url.contains("reddit.com") || url.contains("imgur") || url.contains("redd.it")){
                    continue;
                }


                ContentValues newsValues = new ContentValues();

                newsValues.put(NewsContract.NewsEntry.COLUMN_TITLE, title);
                newsValues.put(NewsContract.NewsEntry.COLUMN_URL, url);

                cVVector.add(newsValues);
            }

            // add to database
            if ( cVVector.size() > 0 ) {
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);

                // delete old data so we don't build up an endless history
                getContentResolver().delete(NewsContract.NewsEntry.CONTENT_URI, null, null);

                getContentResolver().bulkInsert(NewsContract.NewsEntry.CONTENT_URI, cvArray);

            }

            Log.d(LOG_TAG, "Sync Complete. " + cVVector.size() + " Inserted");

        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
    }

}