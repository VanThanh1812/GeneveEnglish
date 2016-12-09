package com.geneve.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.geneve.model.Category;

import java.util.ArrayList;

/**
 * Created by vanthanhbk on 29/11/2016.
 */

public class SQLFunctionCategory {
    private MyDatabaseHelper db;
    private SQLiteDatabase sqLiteDatabase;

    public SQLFunctionCategory(Context context) {
        this.db= new MyDatabaseHelper(context);
    }

    public Category getCategoryById(String id){ // lấy Category theo id

        Category category = new Category();

        sqLiteDatabase = db.getReadableDatabase();

        String sql = "SELECT * FROM " + MyDatabaseHelper.TABLE_CATEGORY+" WHERE "+MyDatabaseHelper.CATEGORY_ID+" = "+id ;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            category.id = cursor.getString(0);
            category.category = cursor.getString(1);

            return category;
        }

        return null;
    }

    public ArrayList<Category> getListCategory(){  // lấy tất cả Category trong db, trả về list<>
        ArrayList<Category> arrCategory = new ArrayList<>();

        sqLiteDatabase = db.getReadableDatabase();

        String sql = "SELECT * FROM "+MyDatabaseHelper.TABLE_CATEGORY;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Category category = new Category();
                category.id = cursor.getString(0);
                category.category = cursor.getString(1);
                arrCategory.add(category);
                Log.d("getcategory",cursor.getString(1));
            }while (cursor.moveToNext());
        }

        return arrCategory;
    }

    public void insertCategory (Category category){ // thêm 1 category

        this.sqLiteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.CATEGORY_ID,category.id);
        values.put(MyDatabaseHelper.CATEGORY_CONTENT,category.category);

        sqLiteDatabase.insert(MyDatabaseHelper.TABLE_CATEGORY,null,values);
        sqLiteDatabase.close();

    }

    public int updateCategory (Category category){ // update 1 category theo id

        this.sqLiteDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.CATEGORY_ID,category.id);
        values.put(MyDatabaseHelper.CATEGORY_CONTENT,category.category);

        return sqLiteDatabase.update(MyDatabaseHelper.TABLE_CATEGORY,
                values,
                MyDatabaseHelper.CATEGORY_ID+"=?",
                new String[]{category.id});
    }

    public int deleteCategory(String id){ // xóa 1 category theo id

        this.sqLiteDatabase = db.getWritableDatabase();

        return sqLiteDatabase.delete(MyDatabaseHelper.TABLE_CATEGORY,
                MyDatabaseHelper.CATEGORY_ID+"=?",
                new String[]{id});
    }

    public void deleteTable(){
        String sql = "DROP TABLE "+MyDatabaseHelper.TABLE_CATEGORY;
        this.sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }
}
