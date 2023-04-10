package com.example.wheaterub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager pager;
    SharedPreferences settingFile;
    int tab_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);
        settingFile=getSharedPreferences("sfile", Context.MODE_PRIVATE);

        if(settingFile.getInt("tab",-1)==-1){
            SharedPreferences.Editor edit=settingFile.edit();
            edit.putInt("tab",0);
            edit.commit();
        }else{
            tab_c=settingFile.getInt("tab",0);
            System.out.println(tab_c);
        }



        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("This week"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pager = findViewById(R.id.pager);

        PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),this);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab_c=tab.getPosition();
                pager.setCurrentItem(tab_c);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.setCurrentItem(tab_c);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor=settingFile.edit();
        editor.putInt("tab",tab_c);
        editor.apply();
    }
}