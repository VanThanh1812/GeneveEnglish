package vanthanh.com.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vanthanh.com.model.Video;

/**
 * Created by vanthanhbk on 21/12/2016.
 */

public class SQLFunctionStatus {

    private MyDatabaseHelper db;
    private SQLiteDatabase sqLiteDatabase;
    private SQLFunctionVideo sqlFunctionVideo;

    public SQLFunctionStatus(Context context) {
        MyDatabaseHelper db = new MyDatabaseHelper(context);
        this.db = db;
        sqlFunctionVideo = new SQLFunctionVideo(context);
    }

    public ArrayList<Video> getListVideoStatus(String status){
        ArrayList<Video> arr = new ArrayList<>();
        arr = sqlFunctionVideo.getAllVideo();
        for (int i = 0; i< arr.size();i++){
            if (arr.get(i).getId() != status){
                arr.remove(i);
            }
        }
        return arr;
    }

    public int checkStatusVideo(String id){

        int i = 0;
        this.sqLiteDatabase = db.getReadableDatabase();
        String sql = "SELECT * FROM "+
                MyDatabaseHelper.TABLE_STATUSVIDEO+
                " WHERE "+MyDatabaseHelper.STATUS_ID+" = "+id;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor != null ){
            cursor.moveToNext();
            Log.d("checkid",cursor.getString(1));
            return Integer.parseInt(cursor.getString(1));
        }
        return i;
    }

    public void insertStatusVideo (String id, String number){

        this.sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MyDatabaseHelper.STATUS_ID, Integer.parseInt(id));
        values.put(MyDatabaseHelper.STATUS_STATUS, number);

        sqLiteDatabase.insert(MyDatabaseHelper.TABLE_STATUSVIDEO,null,values);
        Log.d("insertvideo", id);
        sqLiteDatabase.close();

    }

    public int updateStatus (String id, String number){

        this.sqLiteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.STATUS_ID, Integer.parseInt(id));
        values.put(MyDatabaseHelper.STATUS_STATUS, number);

        return sqLiteDatabase.update(MyDatabaseHelper.TABLE_STATUSVIDEO,values,MyDatabaseHelper.STATUS_ID+"=?",
                new String[] {id});

    }

}
