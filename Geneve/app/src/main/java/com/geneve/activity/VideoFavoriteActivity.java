package com.geneve.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.geneve.R;
import com.geneve.adapter.VideoFavoriteAdaper;

import java.util.ArrayList;

import vanthanh.com.model.Video;
import vanthanh.com.model.database.SQLFunctionStatus;
import vanthanh.com.model.database.SQLFunctionVideo;
import vanthanh.com.model.database.StaticValues;

public class VideoFavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_favorite);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4caf50")));
        getSupportActionBar().setTitle("Yêu thích");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.ic_favourist));

        SQLFunctionStatus sqlFunctionStatus = new SQLFunctionStatus(this);
        SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(this);
        ListView lst = (ListView) findViewById(R.id.lst_favorite_video);
        ArrayList<Video> arr_video = sqlFunctionStatus.getListVideoStatus(
                sqlFunctionVideo.getAllVideo(),
                String.valueOf(StaticValues.FAVOURITE));

        if (arr_video.size() == 0){



        }else {
            TextView textView = (TextView) findViewById(R.id.tv_none);
            textView.setVisibility(View.GONE);
            VideoFavoriteAdaper adaper = new VideoFavoriteAdaper(this,arr_video);
            lst.setAdapter(adaper);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
