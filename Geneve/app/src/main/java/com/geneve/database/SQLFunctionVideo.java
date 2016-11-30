package com.geneve.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.geneve.model.ItemVideo;

import java.util.ArrayList;

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

    public ItemVideo getItemVideoById(String id){ // lấy 1 ItemVideo theo id
        ItemVideo itemVideo = new ItemVideo();
        this.sqLiteDatabase = db.getReadableDatabase();

        String sql = "SELECT * FROM "+
                MyDatabaseHelper.TABLE_VIDEO+
                " WHERE "+MyDatabaseHelper.VIDEO_ID+" = "+id;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor != null){

            cursor.moveToFirst();
            itemVideo.setId(cursor.getString(0));

            Log.d("testcursor",cursor.getString(2));
            itemVideo.setTitle(cursor.getString(1));
            itemVideo.setDescription(cursor.getString(2));
            itemVideo.setThumbnail(cursor.getString(3));
            itemVideo.setUploaded(cursor.getString(4));
            itemVideo.setDuration(cursor.getString(5));
            itemVideo.setContent(cursor.getString(6));
            itemVideo.setRating(cursor.getString(7));
            itemVideo.setViewCount(cursor.getString(8));
            itemVideo.setIdCategory(cursor.getString(9));

            return itemVideo;
        }
        return null;
    }

    public ArrayList<ItemVideo> getAllVideo(){ // lấy tất cả ItemVideo , trả về list<>
        ArrayList<ItemVideo> arrItemVideo = new ArrayList<>();

        this.sqLiteDatabase = db.getReadableDatabase();

        String sql = "SELECT * FROM "+MyDatabaseHelper.TABLE_VIDEO;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do{
                ItemVideo itemVideo = new ItemVideo();

                itemVideo.setId(cursor.getString(0));

                Log.d("testcursor",cursor.getString(2));
                itemVideo.setTitle(cursor.getString(1));
                itemVideo.setDescription(cursor.getString(2));
                itemVideo.setThumbnail(cursor.getString(3));
                itemVideo.setUploaded(cursor.getString(4));
                itemVideo.setDuration(cursor.getString(5));
                itemVideo.setContent(cursor.getString(6));
                itemVideo.setRating(cursor.getString(7));
                itemVideo.setViewCount(cursor.getString(8));
                itemVideo.setIdCategory(cursor.getString(9));

                arrItemVideo.add(itemVideo);

                Log.d("catchvideo","here ");

            }while (cursor.moveToNext());
        }

        return arrItemVideo;
    }

    public void insertVideoSqlite(ItemVideo itemVideo){ // thêm 1 video vào db

        this.sqLiteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MyDatabaseHelper.VIDEO_ID,"12");
        values.put(MyDatabaseHelper.VIDEO_TITLE,itemVideo.getTitle());
        values.put(MyDatabaseHelper.VIDEO_DESCRIPTION,itemVideo.getDescription());
        values.put(MyDatabaseHelper.VIDEO_THUMBNAIL,itemVideo.getThumbnail());
        values.put(MyDatabaseHelper.VIDEO_UPLOADED,itemVideo.getUploaded());
        values.put(MyDatabaseHelper.VIDEO_DURATION,itemVideo.getDuration());
        values.put(MyDatabaseHelper.VIDEO_CONTENT,itemVideo.getContent());
        values.put(MyDatabaseHelper.VIDEO_RATING,itemVideo.getRating());
        values.put(MyDatabaseHelper.VIDEO_VIEWCOUNT,itemVideo.getViewCount());
        values.put(MyDatabaseHelper.VIDEO_IDCATEGORY,itemVideo.getIdCategory());

        sqLiteDatabase.insert(MyDatabaseHelper.TABLE_VIDEO,null,values);

        sqLiteDatabase.close();

    }

    public int updateVideoSqlite(ItemVideo itemVideo){ // update
        this.sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MyDatabaseHelper.VIDEO_ID,"12");
        values.put(MyDatabaseHelper.VIDEO_TITLE,itemVideo.getTitle());
        values.put(MyDatabaseHelper.VIDEO_DESCRIPTION,itemVideo.getDescription());
        values.put(MyDatabaseHelper.VIDEO_THUMBNAIL,itemVideo.getThumbnail());
        values.put(MyDatabaseHelper.VIDEO_UPLOADED,itemVideo.getUploaded());
        values.put(MyDatabaseHelper.VIDEO_DURATION,itemVideo.getDuration());
        values.put(MyDatabaseHelper.VIDEO_CONTENT,itemVideo.getContent());
        values.put(MyDatabaseHelper.VIDEO_RATING,itemVideo.getRating());
        values.put(MyDatabaseHelper.VIDEO_VIEWCOUNT,itemVideo.getViewCount());
        values.put(MyDatabaseHelper.VIDEO_IDCATEGORY,itemVideo.getIdCategory());

        return sqLiteDatabase.update(MyDatabaseHelper.TABLE_VIDEO,values,MyDatabaseHelper.VIDEO_ID+"=?",
                new String[] {itemVideo.getId()});

    }

    public int deleteVideoSqlite(String id){ //delete
        this.sqLiteDatabase = db.getWritableDatabase();
        return sqLiteDatabase.delete(MyDatabaseHelper.TABLE_VIDEO,
                MyDatabaseHelper.VIDEO_ID+"=?",
                new String[]{id});
    }

}
