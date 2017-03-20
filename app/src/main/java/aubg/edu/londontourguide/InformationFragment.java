package aubg.edu.londontourguide;


<<<<<<< HEAD
<<<<<<< HEAD
import android.database.Cursor;
=======
import android.content.Intent;
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
=======
import android.content.Intent;
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
<<<<<<< HEAD
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import aubg.edu.londontourguide.adapters.NewsAdapter;
import aubg.edu.londontourguide.data.NewsContract;
=======
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
=======
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int INFORMATION_LOADER = 0;

    private static final String[] NEWS_COLUMNS = new String[] {
            NewsContract.NewsEntry._ID,
            NewsContract.NewsEntry.COLUMN_TITLE,
            NewsContract.NewsEntry.COLUMN_URL
    };

    private static final int INDEX_NEWS_ID = 0;
    private static final int INDEX_NEWS_TITLE = 1;
    private static final int INDEX_NEWS_URL = 2;

<<<<<<< HEAD
    private Uri mUri = NewsContract.NewsEntry.CONTENT_URI;

<<<<<<< HEAD
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

=======
Button btnVideo;
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
=======
Button btnVideo;
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
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

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new NewsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
<<<<<<< HEAD
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(INFORMATION_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if ( null != mUri ) {
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
<<<<<<< HEAD
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.deleteAllData();
=======
=======
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnVideo = (Button)view.findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=jiz0uJaFFII")));
                Log.i("Video", "Video Playing....");

            }
        });
<<<<<<< HEAD
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
=======
>>>>>>> 8dc89d73508f7752debf892308e71ddeaf3cf412
    }
}
