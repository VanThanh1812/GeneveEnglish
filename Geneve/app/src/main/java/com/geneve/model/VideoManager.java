package com.geneve.model;

import android.content.Context;
import android.util.Log;

import com.geneve.database.SQLFunctionVideo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by vanthanhbk on 27/11/2016.
 */

public class VideoManager { // quản lý video

    private Context activity;
    private ItemVideo itemVideo;
    private DatabaseReference reference;

    public VideoManager(Context activity) { // khởi tạo
        this.activity = activity;
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public VideoManager(Context activity, ItemVideo itemVideo) {
        this.activity = activity;
        this.itemVideo = itemVideo;
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public void postComment (Comment comment) {  // tải 1 nội dung comment vào itemVideo ở trên
        reference.child("video").child(itemVideo.getId()).child("comment").child(comment.id).setValue(new Comment(comment.name,comment.textDisplay));
    }

    public void postVideo (ItemVideo video){ // tải 1 nội dung video lên server
        ArrayList<Comment> arrayList = new ArrayList<>();
        arrayList.add(new Comment("1","Hay"));
        arrayList.add(new Comment("2","Qua"));
        video.setListcomment(arrayList);
        reference.child("video").child(video.getId()).setValue(video);
    }

    public void getAllVideo(){ // lấy tất cả video server về local
        reference.child("video").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) { // lấy tất cả video đc add

                ItemVideo itemVideo = dataSnapshot.getValue(ItemVideo.class);

                SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(activity);

                sqlFunctionVideo.insertVideoSqlite(itemVideo);

                Log.d("videoitem",itemVideo.toString());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { // lắng nghe nếu có sự thay đổi trên server thì update
                ItemVideo itemVideo = dataSnapshot.getValue(ItemVideo.class);
                SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(activity);
                sqlFunctionVideo.updateVideoSqlite(itemVideo);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { // nếu bị xóa thì cũng xóa luôn trong server
                ItemVideo itemVideo = dataSnapshot.getValue(ItemVideo.class);
                SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(activity);
                sqlFunctionVideo.deleteVideoSqlite(itemVideo.getId());
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
