package com.geneve.model;

import android.content.Context;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vanthanh.com.model.Category;
import vanthanh.com.model.database.SQLFunctionCategory;

/**
 * Created by vanthanhbk on 30/11/2016.
 */

public class CategoryManager  {  // quản lý hàm đối với Category

    private Context context;
    private Category category;
    private DatabaseReference reference;

    public CategoryManager(Context context) { // khởi tạo
        this.context = context;
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public CategoryManager(Context context, Category category) { // khởi tạo
        this.context = context;
        this.category = category;
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public void postCategory(){ // post 1 đối tượng Category lên server
        reference.child("category").child(category.id).setValue(category.category);
    }



    public void getAllCategory(){ // lấy tất cả đối tượng về
        reference.child("category").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {   // lấy các child

                Category category = new Category(dataSnapshot.getKey(),dataSnapshot.getValue().toString());

                SQLFunctionCategory sqlFunctionCategory = new SQLFunctionCategory(context);
                sqlFunctionCategory.insertCategory(category);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {  // lắng nghe update realtime trên server, có thay đổi là update local ngay

                Category category = new Category(dataSnapshot.getKey(),dataSnapshot.getValue().toString());
                SQLFunctionCategory sqlFunctionCategory = new SQLFunctionCategory(context);
                sqlFunctionCategory.updateCategory(category);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {  // lắng nghe delete trên server , xóa ở server là về cũng xóa ngay ở local

                Category category = new Category(dataSnapshot.getKey(),dataSnapshot.getValue().toString());
                SQLFunctionCategory sqlFunctionCategory = new SQLFunctionCategory(context);
                sqlFunctionCategory.deleteCategory(category.id);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
