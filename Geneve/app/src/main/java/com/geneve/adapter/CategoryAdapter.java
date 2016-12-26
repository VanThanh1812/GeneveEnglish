package com.geneve.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.geneve.R;

import java.util.ArrayList;

import vanthanh.com.model.Category;
import vanthanh.com.model.Video;
import vanthanh.com.model.database.SQLFunctionVideo;

/**
 * Created by vanthanhbk on 02/12/2016.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {

    private Context context;
    private ArrayList<Category> arrayCategory;

    public CategoryAdapter(Context context, ArrayList<Category> obj) {
        super(context, 0, obj);
        this.context = context;
        this.arrayCategory = obj;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_main,null);
        TextView tv_category = (TextView) view.findViewById(R.id.tv_category);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recy_itemvideo);

        Category category = arrayCategory.get(position);

        tv_category.setText(category.category);

        SQLFunctionVideo sqlFunctionVideo = new SQLFunctionVideo(context);

        ArrayList<Video> arrVideo = sqlFunctionVideo.getAllVideoByCategory(context,category.id);

        final VideoAdapter adapter = new VideoAdapter(context,arrVideo);

        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(manager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);


//        final ArrayList<Video> finalArrVideo = arrVideo;
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(context, finalArrVideo.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));

        return view;
    }

}
