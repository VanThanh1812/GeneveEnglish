package com.geneve.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.geneve.R;
import com.geneve.adapter.VideoIgnoreAdaper;

import vanthanh.com.model.database.SQLFunctionStatus;
import vanthanh.com.model.database.StaticValues;

public class VideoIgnoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_ignore);

        SQLFunctionStatus sqlFunctionStatus = new SQLFunctionStatus(this);
        ListView lst = (ListView) findViewById(R.id.lst_ignore_video);
        VideoIgnoreAdaper adaper = new VideoIgnoreAdaper(this,sqlFunctionStatus.getListVideoStatus(String.valueOf(StaticValues.IGNORE)));
        lst.setAdapter(adaper);
    }
}
