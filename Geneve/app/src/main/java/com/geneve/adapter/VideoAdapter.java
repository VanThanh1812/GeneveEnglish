package com.geneve.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.geneve.R;

import java.util.ArrayList;

import karu.videoview.WatchVideo;
import vanthanh.com.model.Video;
import vanthanh.com.model.database.SQLFunctionStatus;
import vanthanh.com.model.database.StaticValues;

/**
 * Created by vanthanhbk on 02/12/2016.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Video> arrayVideo;

    public class MyViewHolder extends RecyclerView.ViewHolder { // khoi tao lop

        public TextView tv_title;
        public ImageView iv_thumbail, iv_popup;
        public TextView tv_uploaded;
        public TextView tv_rating;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_thumbail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            tv_uploaded = (TextView) itemView.findViewById(R.id.tv_uploaded);
            tv_rating = (TextView) itemView.findViewById(R.id.tv_rating);
            iv_popup = (ImageView) itemView.findViewById(R.id.iv_popup_menu);
        }
    }

    public VideoAdapter(Context context, ArrayList<Video> arrayVideo) {

        this.context = context;
        this.arrayVideo = arrayVideo;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("getViewHolder","holder");
        View itemVideo = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_recyclerview_video,null,false);
        return new MyViewHolder(itemVideo);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Video video = arrayVideo.get(position);
        holder.tv_rating.setText(video.getRating());
        holder.tv_uploaded.setText(video.getUploaded());
        holder.tv_title.setText(video.getTitle());
        Glide.with(context).load(video.getThumbnail()).placeholder(R.drawable.ic_default).into(holder.iv_thumbail);
        holder.iv_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.iv_popup, video);
            }
        });

        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivityVideo(position);
            }
        });
        holder.iv_thumbail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivityVideo(position);
            }
        });
    }

    private void intentActivityVideo(int pos) {
        Video video = arrayVideo.get(pos);
        Intent wordLayout = new Intent(context, WatchVideo.class);
        wordLayout.putExtra("object",video);
        wordLayout.putExtra("state",false);
        wordLayout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(wordLayout);
    }


    /**
     * Showing popup menu when tapping on 3 dots
     */

    private void showPopupMenu(View popup, Video video) {
        // inflate menu
        PopupMenu popupMenu = new PopupMenu(popup.getContext(),popup);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_video,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener(video));
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return arrayVideo.size();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private Video video;

        SQLFunctionStatus sqlFunctionStatus;

        public MyMenuItemClickListener(Video video) {
            this.video = video;
            sqlFunctionStatus = new SQLFunctionStatus(context);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_add_favourite:
                    Toast.makeText(context, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                    sqlFunctionStatus.updateStatus(String.valueOf(video.getId()),String.valueOf(StaticValues.FAVOURITE));
                    return true;
                case R.id.action_add_watch_later:
                    Toast.makeText(context, "Đã thêm vào xem sau", Toast.LENGTH_SHORT).show();
                    sqlFunctionStatus.updateStatus(String.valueOf(video.getId()),String.valueOf(StaticValues.WATCHLATER));
                    return true;
                case R.id.action_ignore_video:
                    Toast.makeText(context, "Video này sẽ chỉ hiển thị trong danh sách bỏ qua", Toast.LENGTH_SHORT).show();
                    sqlFunctionStatus.updateStatus(String.valueOf(video.getId()),String.valueOf(StaticValues.IGNORE));
                    arrayVideo.remove(video);
                    notifyDataSetChanged();
                    return true;
                default:
            }
            return false;
        }
    }
}
