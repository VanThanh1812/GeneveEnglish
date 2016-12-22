package vanthanh.com.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vanthanh.com.model.Video;

/**
 * Created by vanthanhbk on 29/11/2016.
 */

public class SQLFunctionVideo {
    private MyDatabaseHelper db;
    private SQLiteDatabase sqLiteDatabase;

    public SQLFunctionVideo(Context context) {
        MyDatabaseHelper db = new MyDatabaseHelper(context);
        this.db = db;

    }

    public Video getItemVideoById(String id){ // lấy 1 Video theo id
        Video video = new Video();
        this.sqLiteDatabase = db.getReadableDatabase();

        String sql = "SELECT * FROM "+
                MyDatabaseHelper.TABLE_VIDEO+
                " WHERE "+MyDatabaseHelper.VIDEO_ID+" = "+id;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor != null){

            cursor.moveToFirst();
            video.setId(cursor.getString(0));

            Log.d("testcursor",cursor.getString(2));
            video.setTitle(cursor.getString(1));
            video.setDescription(cursor.getString(2));
            video.setThumbnail(cursor.getString(3));
            video.setUploaded(cursor.getString(4));
            video.setDuration(cursor.getString(5));
            video.setContent(cursor.getString(6));
            video.setRating(cursor.getString(7));
            video.setViewCount(cursor.getString(8));
            video.setIdCategory(cursor.getString(9));

            return video;
        }
        return null;
    }

    public ArrayList<Video> getAllVideo(){ // lấy tất cả Video theo id Category, trả về list<>

        ArrayList<Video> arrVideo = new ArrayList<>();

        this.sqLiteDatabase = db.getReadableDatabase();

        String sql = "SELECT * FROM "+MyDatabaseHelper.TABLE_VIDEO;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            do{

                Video video = new Video();

                video.setId(cursor.getString(0));
                video.setTitle(cursor.getString(1));
                video.setDescription(cursor.getString(2));
                video.setThumbnail(cursor.getString(3));
                video.setUploaded(cursor.getString(4));
                video.setDuration(cursor.getString(5));
                video.setContent(cursor.getString(6));
                video.setRating(cursor.getString(7));
                video.setViewCount(cursor.getString(8));
                video.setIdCategory(cursor.getString(9));

                arrVideo.add(video);

            }while (cursor.moveToNext());

        } else return null;

        return arrVideo;
    }

    public ArrayList<Video> getAllVideoByCategory(String id){ // lấy tất cả Video theo id Category, trả về list<>

        ArrayList<Video> arrVideo = new ArrayList<>();

        this.sqLiteDatabase = db.getReadableDatabase();

        String sql = "SELECT * FROM "+MyDatabaseHelper.TABLE_VIDEO+" WHERE "+MyDatabaseHelper.VIDEO_IDCATEGORY +" = "+id;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            do{

                Video video = new Video();

                video.setId(cursor.getString(0));
                video.setTitle(cursor.getString(1));
                video.setDescription(cursor.getString(2));
                video.setThumbnail(cursor.getString(3));
                video.setUploaded(cursor.getString(4));
                video.setDuration(cursor.getString(5));
                video.setContent(cursor.getString(6));
                video.setRating(cursor.getString(7));
                video.setViewCount(cursor.getString(8));
                video.setIdCategory(cursor.getString(9));


                    arrVideo.add(video);

            }while (cursor.moveToNext());

        } else return null;

        return arrVideo;
    }

    public void insertVideoSqlite(Video video){ // thêm 1 video vào db

        this.sqLiteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MyDatabaseHelper.VIDEO_ID, video.getId());
        values.put(MyDatabaseHelper.VIDEO_TITLE, video.getTitle());
        values.put(MyDatabaseHelper.VIDEO_DESCRIPTION, video.getDescription());
        values.put(MyDatabaseHelper.VIDEO_THUMBNAIL, video.getThumbnail());
        values.put(MyDatabaseHelper.VIDEO_UPLOADED, video.getUploaded());
        values.put(MyDatabaseHelper.VIDEO_DURATION, video.getDuration());
        values.put(MyDatabaseHelper.VIDEO_CONTENT, video.getContent());
        values.put(MyDatabaseHelper.VIDEO_RATING, video.getRating());
        values.put(MyDatabaseHelper.VIDEO_VIEWCOUNT, video.getViewCount());
        values.put(MyDatabaseHelper.VIDEO_IDCATEGORY, video.getIdCategory());

        sqLiteDatabase.insert(MyDatabaseHelper.TABLE_VIDEO,null,values);
        Log.d("insertvideo", video.getTitle());
        sqLiteDatabase.close();

    }

    public int updateVideoSqlite(Video video){ // update
        this.sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MyDatabaseHelper.VIDEO_ID, video.getId());
        values.put(MyDatabaseHelper.VIDEO_TITLE, video.getTitle());
        values.put(MyDatabaseHelper.VIDEO_DESCRIPTION, video.getDescription());
        values.put(MyDatabaseHelper.VIDEO_THUMBNAIL, video.getThumbnail());
        values.put(MyDatabaseHelper.VIDEO_UPLOADED, video.getUploaded());
        values.put(MyDatabaseHelper.VIDEO_DURATION, video.getDuration());
        values.put(MyDatabaseHelper.VIDEO_CONTENT, video.getContent());
        values.put(MyDatabaseHelper.VIDEO_RATING, video.getRating());
        values.put(MyDatabaseHelper.VIDEO_VIEWCOUNT, video.getViewCount());
        values.put(MyDatabaseHelper.VIDEO_IDCATEGORY, video.getIdCategory());

        return sqLiteDatabase.update(MyDatabaseHelper.TABLE_VIDEO,values,MyDatabaseHelper.VIDEO_ID+"=?",
                new String[] {video.getId()});

    }

    public int deleteVideoSqlite(String id){ //delete
        this.sqLiteDatabase = db.getWritableDatabase();
        return sqLiteDatabase.delete(MyDatabaseHelper.TABLE_VIDEO,
                MyDatabaseHelper.VIDEO_ID+"=?",
                new String[]{id});
    }

    public void deleteTable(){
        String sql = "DROP TABLE "+MyDatabaseHelper.TABLE_VIDEO;
        this.sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

}
