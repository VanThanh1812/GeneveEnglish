package vanthanh.com.model;

/**
 * Created by vanthanhbk on 27/11/2016.
 */

public class Comment {
    public String id;
    public String name;
    public String textDisplay;

    public Comment() {
        initComment();
    }

    private void initComment() {
        this.id = "0";
        this.name = "None";
        this.textDisplay = "Null";
    }

    public Comment(String id, String name, String textDisplay) {
        this.id = id;
        this.name = name;
        this.textDisplay = textDisplay;
    }

    public Comment(String name, String textDisplay) {
        this.name = name;
        this.textDisplay = textDisplay;
    }
}
