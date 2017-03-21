package aubg.edu.londontourguide;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import aubg.edu.londontourguide.adapters.NewsAdapter;
import aubg.edu.londontourguide.data.NewsContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int INFORMATION_LOADER = 0;

    private static final String[] NEWS_COLUMNS = new String[]{
            NewsContract.NewsEntry._ID,
            NewsContract.NewsEntry.COLUMN_TITLE,
            NewsContract.NewsEntry.COLUMN_URL
    };

    private Uri mUri = NewsContract.NewsEntry.CONTENT_URI;

    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button btnVideo;
    private ImageView londonMainImage;

    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        /*
        set focusable to false, because otherwise
        the RecyclerView thats focus and the app
        starts centered on it, with the image, button,
        and description on top being cut-out (and the
        user would not know he/she can scroll to them).
        */
        mRecyclerView.setFocusable(false);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NewsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        btnVideo = (Button) view.findViewById(R.id.btnVideo);
        londonMainImage = (ImageView) view.findViewById(R.id.london_main_image);

        View.OnClickListener videoClickListener = new View.OnClickListener() {

            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=jiz0uJaFFII")));
                Log.i("Video", "Video Playing....");

            }
        };

        btnVideo.setOnClickListener(videoClickListener);
        londonMainImage.setOnClickListener(videoClickListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(INFORMATION_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (null != mUri) {
            // Now create and return a CursorLoader that will take care of
            // creating a Cursor for the data being displayed.
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    NEWS_COLUMNS,
                    null,
                    null,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.deleteAllData();
        mAdapter.addFromCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        mAdapter.deleteAllData();
    }
}
