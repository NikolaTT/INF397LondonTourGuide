package aubg.edu.londontourguide;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    public HistoryFragment() {
        // Required empty public constructor
    }
    WebView browser;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Displaying a website using Android WebView
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        browser = (WebView) view.findViewById(R.id.webview);
        browser.loadUrl("https://en.m.wikipedia.org/wiki/History_of_London");

        return view;
    }
}
