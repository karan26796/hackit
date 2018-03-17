package app.hackit.hackamigo.model;

/**
 * Created by karan on 3/17/2018.
 */

public class Options {

    String title,subtitle;

    public Options(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
