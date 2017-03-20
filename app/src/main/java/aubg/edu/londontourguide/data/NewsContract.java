package aubg.edu.londontourguide.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by nikola on 18.03.17.
 */

public class NewsContract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "edu.aubg.londontourguide.newsprovider";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_NEWS = "news";


    /* Inner class that defines the table contents of the location table */
    public static final class NewsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NEWS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;

        // Table name
        public static final String TABLE_NAME = "news";

        // The location setting string is what will be sent to openweathermap
        // as the location query.
        public static final String COLUMN_TITLE = "news_title";
        public static final String COLUMN_URL = "news_url";

        public static final int INDEX_NEWS_ID = 0;
        public static final int INDEX_NEWS_TITLE = 1;
        public static final int INDEX_NEWS_URL = 2;

        public static Uri buildNewsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
