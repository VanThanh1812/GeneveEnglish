package com.geneve;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.geneve.adapter.CategoryAdapter;
import com.geneve.model.CategoryManager;
import com.geneve.model.VideoManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import vanthanh.com.model.database.SQLFunctionCategory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainFunction();
    }

    private void mainFunction() {

//        Video i3 = new Video("3","Chuyến đi gần nhất","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"1");
//
//        Video i4 = new Video("4","Giao thông Việt Nam","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"2");
//
//        Video i5 = new Video("5","Nói về gia đình","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"3");
//
//        Video i6 = new Video("6","Chào hỏi cơ bản","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"0");
//
//        Video i7 = new Video("7","Kỷ niệm nhớ nhất","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"1");
//
//        Video i8 = new Video("8","Thành viên gia đình","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"2");
//
//        Video i9 = new Video("9","Cảnh đẹp Hà Nội","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"1");
//
//        Video i10 = new Video("10","Vẻ đẹp Hồ Gươm","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"1");
//
//        Video i11 = new Video("11","Phố Cổ","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"1");
//

//        videoManager.postVideo(i3);
//        videoManager.postVideo(i4);
//        videoManager.postVideo(i5);
//        videoManager.postVideo(i6);
//        videoManager.postVideo(i7);
//        videoManager.postVideo(i8);
//        videoManager.postVideo(i9);
//        videoManager.postVideo(i10);
//        videoManager.postVideo(i11);

        VideoManager videoManager = new VideoManager(this);
        CategoryManager categoryManager = new CategoryManager(this);

        SQLFunctionCategory sqlFunctionCategory = new SQLFunctionCategory(this);

        videoManager.getAllVideo();
        categoryManager.getAllCategory();

//        String response = null;
//        try{
//            URL url = new URL("localhost:8888/user");
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            //
//            InputStream in = new BufferedInputStream(conn.getInputStream());
//            response = convertToString(in);
//            Log.d("jsonnodejs",response);
//        } catch (Exception e) {
//            Log.e("jsonnodejs", "Exception: " + e.getMessage());
//        }


        ListView listView = (ListView) findViewById(R.id.lst);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,sqlFunctionCategory.getListCategory());
        listView.setAdapter(categoryAdapter);

    }

    private String convertToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
