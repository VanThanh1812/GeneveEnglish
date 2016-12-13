package vanthanh.com.model;

/**
 * Created by hoang on 02/12/2016.
 */

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by vanthanhbk on 27/11/2016.
 */

public class Video implements Serializable{

    private String id;
    private String title;
    private String description;
    private String thumbnail;   //link image
    private String uploaded;    //time uploaded
    private String duration;  //time video
    private String content;
    private String rating;
    private String viewCount;
    private ArrayList<vanthanh.com.model.Comment> listcomment;
    private String idCategory;

    public Video() {
        initVideo();
    }

    private void initVideo() {
        this.id = "0";
        this.title = "Null";
        this.description = "Null";
        this.thumbnail = "Null";
        this.uploaded = "Null";
        this.duration = "0";
        this.content = "Null";
        this.rating = "0";
        this.viewCount = "0";
        this.listcomment = null;
        this.idCategory = "0";
    }

    @Override
    public String toString() {
        return "ItemVideo{" +
                "content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", uploaded='" + uploaded + '\'' +
                ", duration='" + duration + '\'' +
                ", rating='" + rating + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", listcomment=" + listcomment +
                ", idCategory='" + idCategory + '\'' +
                '}';
    }

    public ArrayList<vanthanh.com.model.Comment> getListcomment() {
        return listcomment;
    }

    public void setListcomment(ArrayList<vanthanh.com.model.Comment> listcomment) {
        this.listcomment = listcomment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

}
