package aubg.edu.londontourguide;

/**
 * Created by nikola on 20.03.17.
 */

public class News {

    private String title;
    private String url;

    public News(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {

        return title;
    }

    public String getUrl() {
        return url;
    }
}
