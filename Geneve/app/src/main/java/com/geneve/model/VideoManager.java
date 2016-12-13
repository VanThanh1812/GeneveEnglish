package com.geneve.model;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vanthanh.com.model.Comment;
import vanthanh.com.model.Video;
import vanthanh.com.model.database.SQLFunctionVideo;

/**
 * Created by vanthanhbk on 27/11/2016.
 */

public class VideoManager { // quản lý video

    private Context activity;
    private Video video;
    private DatabaseReference reference;

    public VideoManager(Context activity) { // khởi tạo
        this.activity = activity;
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public VideoManager(Context activity, Video video) {
        this.activity = activity;
        this.video = video;
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public void postComment (Comment comment) {  // tải 1 nội dung comment vào video ở trên
        reference.child("video").child(video.getId()).child("comment").child(comment.id).setValue(new Comment(comment.name,comment.textDisplay));
    }

    public void postVideo (Video video){ // tải 1 nội dung video lên server
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

                Video video = dataSnapshot.getValue(Video.class);

                SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(activity);

                sqlFunctionVideo.insertVideoSqlite(video);

                Log.d("videoitem", video.toString());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { // lắng nghe nếu có sự thay đổi trên server thì update
                Video video = dataSnapshot.getValue(Video.class);
                SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(activity);
                sqlFunctionVideo.updateVideoSqlite(video);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { // nếu bị xóa thì cũng xóa luôn trong server
                Video video = dataSnapshot.getValue(Video.class);
                SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(activity);
                sqlFunctionVideo.deleteVideoSqlite(video.getId());
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
