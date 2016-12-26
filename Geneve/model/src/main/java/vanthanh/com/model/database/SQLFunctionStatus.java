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

    public SQLFunctionStatus(Context context) {
        MyDatabaseHelper db = new MyDatabaseHelper(context);
        this.db = db;
        this.sqLiteDatabase = db.getReadableDatabase();
    }

    public ArrayList<Video> getListVideoStatus(ArrayList<Video> arr ,String status){ // lấy ra list video thỏa mãn cái status kia

        ArrayList<Video> arr2 = new ArrayList<>(); // arr nhận dữ liệu

        for (int i = 0; i< arr.size();i++){
            String id_video = arr.get(i).getId(); // lấy ra id video để truy vấn trang thái
            String sql = "SELECT * FROM "+
                    MyDatabaseHelper.TABLE_STATUSVIDEO+
                    " WHERE "+MyDatabaseHelper.STATUS_ID+" = "+id_video;
            Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

            if (cursor.moveToNext() ){
                if (cursor.getString(1).equals(status)){
                    arr2.add(arr.get(i));
                };
                Log.i("filter video", String.valueOf(i)+"--"+status+"--"+cursor.getString(1));
            }
        }
        Log.d("sizearr", String.valueOf(arr2.size()));
        return arr2;
    }

//    public boolean checkStatusVideo(String id){ // check video có id là id , nếu là ignore thì ko show , nếu khác ignore thì show
//
//        int i = 0;
//        String sql = "SELECT * FROM "+
//                MyDatabaseHelper.TABLE_STATUSVIDEO+
//                " WHERE "+MyDatabaseHelper.STATUS_ID+" = "+id;
//
//        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
//        if (cursor != null ){
//            cursor.moveToNext();
//            Log.d("checkid",cursor.getString(1));
//            return (Integer.parseInt(cursor.getString(1)) != StaticValues.IGNORE);
//        }
//        return false;
//    }

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

    public void deleteTable(){
        String sql = "DROP TABLE "+MyDatabaseHelper.TABLE_STATUSVIDEO;
        this.sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
}
