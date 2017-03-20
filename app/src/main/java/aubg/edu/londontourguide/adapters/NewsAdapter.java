package aubg.edu.londontourguide.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import aubg.edu.londontourguide.News;
import aubg.edu.londontourguide.R;
import aubg.edu.londontourguide.data.NewsContract;

/**
 * Created by nikola on 20.03.17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> newsList;
    private Context context;

    public NewsAdapter(ArrayList<News> newsList) {
        this.newsList = newsList;
    }


    public NewsAdapter(Cursor newsCursor){
        newsList = new ArrayList<>();
        while(newsCursor.moveToNext()){
            String title = newsCursor.getString(NewsContract.NewsEntry.INDEX_NEWS_URL);
            String url = newsCursor.getString(NewsContract.NewsEntry.INDEX_NEWS_TITLE);
            newsList.add(new News(title, url));
        }
    }

    public NewsAdapter(Context context) {
        newsList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        String title = newsList.get(position).getTitle();
        final String url = newsList.get(position).getUrl();

        holder.newsTitle.setText(title);
        holder.newsURL.setText(url);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addFromCursor(Cursor newsCursor){
        while(newsCursor.moveToNext()){
            String title = newsCursor.getString(NewsContract.NewsEntry.INDEX_NEWS_TITLE);
            String url = newsCursor.getString(NewsContract.NewsEntry.INDEX_NEWS_URL);
            newsList.add(new News(title, url));
        }
        notifyDataSetChanged();
    }

    public void deleteAllData(){
        newsList.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View container;
        public TextView newsTitle;
        public TextView newsURL;

        public ViewHolder(View itemView) {
            super(itemView);
            container = itemView;
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsURL = (TextView) itemView.findViewById(R.id.news_url);
        }
    }
}
