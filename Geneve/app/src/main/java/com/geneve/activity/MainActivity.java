package com.geneve.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.geneve.R;
import com.geneve.adapter.CategoryAdapter;
import com.geneve.model.CategoryManager;
import com.geneve.model.VideoManager;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import vanthanh.com.model.database.SQLFunctionCategory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialSearchView searchView;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setSearchView();
        mainFunction();
    }

    private void setSearchView() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(true);
        searchView.setHint("Enter key word");
        searchView.setHintTextColor(Color.GRAY);
        searchView.setVoiceIcon(getResources().getDrawable(android.R.drawable.ic_btn_speak_now));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void mainFunction() {

//        Video i3 = new Video("2","Chuyến đi gần nhất","Chủ đề video là cách chào hỏi khi mới gặp",
//                "http:abc","2016-12-03 07:23:23","12ph","http:abc","4.2","123",null,"0");
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

        categoryManager.getAllCategory();

        videoManager.getAllVideo();

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

        if (searchView.isSearchOpen()){
            searchView.closeSearch();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favourite) {
            Intent i = new Intent(this, VideoFavoriteActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_infomation) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK){
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0){
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)){
                    searchView.setQuery(searchWrd,false);
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        navigationView.setCheckedItem(R.id.nav_main);
        super.onResume();
    }
}
