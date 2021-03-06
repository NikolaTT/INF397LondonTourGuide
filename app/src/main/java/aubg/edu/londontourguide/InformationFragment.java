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
public class InformationFragment extends Fragment{

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

}
