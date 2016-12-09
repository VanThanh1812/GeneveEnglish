package com.geneve.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vanthanhbk on 29/11/2016.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper { // khởi tạo các bảng database

    private Context context;
    private static final String TAG = "SQLite";
    // Phiên bản
    private static final int DATABASE_VERSION = 1;
    //tên database
    private static final String DATABASE_NAME = "DatabaseGeneve";

    // danh sách bảng
    public static final String TABLE_VIDEO = "TableVideo";
    public static final String TABLE_CATEGORY = "TableCategory";

    // bảng Table video
    /*
    * private String id;
    private String title;
    private String description;
    private String thumbnail;   //link image
    private String uploaded;    //time uploaded
    private String duration;  //time video
    private String content;
    private String rating;
    private String viewCount;
    private ArrayList<Comment> listcomment;
    private String idCategory;
    * */
    public static final String VIDEO_ID = "_id";
    public static final String VIDEO_TITLE= "title";
    public static final String VIDEO_DESCRIPTION= "description";
    public static final String VIDEO_THUMBNAIL= "thumbnail";
    public static final String VIDEO_UPLOADED= "uploaded";
    public static final String VIDEO_DURATION= "duration";
    public static final String VIDEO_CONTENT= "content";
    public static final String VIDEO_RATING= "rating";
    public static final String VIDEO_VIEWCOUNT= "viewCount";
    public static final String VIDEO_IDCATEGORY= "idCategory";
//
    private String script_tableVideo = "CREATE TABLE if not exists " + TABLE_VIDEO+" ("+
            VIDEO_ID+ " INTEGER PRIMARY KEY NOT NULL, "+
            VIDEO_TITLE+" TEXT,"+
            VIDEO_DESCRIPTION+" TEXT,"+
            VIDEO_THUMBNAIL+" TEXT,"+
            VIDEO_UPLOADED+" TEXT,"+
            VIDEO_DURATION+" TEXT,"+
            VIDEO_CONTENT+" TEXT,"+
            VIDEO_RATING+" TEXT,"+
            VIDEO_VIEWCOUNT+" TEXT,"+
            VIDEO_IDCATEGORY+" INTEGER"+
            ");";

    // Bảng category

    public static final String CATEGORY_ID="_id";
    public static final String CATEGORY_CONTENT="category";
// PRIMARY KEY
    private String script_tableCategory = "CREATE TABLE if not exists "+TABLE_CATEGORY+" ("+
            CATEGORY_ID+" INTEGER PRIMARY KEY NOT NULL, "+
            CATEGORY_CONTENT+" TEXT"+
            ");";


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(script_tableVideo);
        sqLiteDatabase.execSQL(script_tableCategory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        onCreate(sqLiteDatabase);
    }
}
