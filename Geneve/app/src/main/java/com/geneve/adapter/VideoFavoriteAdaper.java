package com.geneve.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geneve.R;

import java.util.ArrayList;

import vanthanh.com.model.Video;

/**
 * Created by vanthanhbk on 22/12/2016.
 */

public class VideoFavoriteAdaper extends ArrayAdapter<Video> {

    private Context context;
    private ArrayList<Video> arr;

    public VideoFavoriteAdaper(Context context, ArrayList<Video> list) {
        super(context, 0, list);
        this.context = context;
        this.arr = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_video_favorite,null);
        Video video = arr.get(position);

        ImageView img = (ImageView) v.findViewById(R.id.ig_img);
        TextView tv_title = (TextView) v.findViewById(R.id.ig_tv_title);
        TextView tv_timeup = (TextView) v.findViewById(R.id.ig_tv_uploaded);
        TextView tv_rating = (TextView) v.findViewById(R.id.ig_tv_rating);
        ImageView iv_popup = (ImageView) v.findViewById(R.id.ig_iv_popup_menu);

        Glide.with(context).load(video.getThumbnail()).placeholder(R.drawable.ic_default).into(img);
        tv_title.setText(video.getTitle());
        tv_rating.setText(video.getRating());
        tv_timeup.setText(video.getUploaded());

        return  v;
    }
}
