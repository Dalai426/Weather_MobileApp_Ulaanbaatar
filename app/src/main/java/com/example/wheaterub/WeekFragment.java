package com.example.wheaterub;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wheaterub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.zip.Inflater;

import models.DayTemp;
import models.TimeTemp;
import recycleradapter.RecyclerAdapter;
import recycleradapter.WeekRecyclerAdapter;
import weather.IconIm;


public class WeekFragment extends Fragment {

    RecyclerView r_week;
    LinkedList<DayTemp> tempDays ;
    LayoutInflater inflater;
    WeekRecyclerAdapter rec_adapter;
    public WeekFragment(Context context) {
        String url_1="https://api.openweathermap.org/data/2.5/onecall?lat=47.921230&lon=106.918556&exclude=dialy&units=metric&appid=e53301e27efa0b66d05045d91b2742d3&fbclid=IwAR0Dzb4keNyv6GkAiEMtJrtzY8zYr6u2CWCW2pmp03gsALGiKLxIsKC0KCs";
        StringRequest request=new StringRequest(Request.Method.POST, url_1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsob=new JSONObject(response);
                    JSONArray daily=jsob.getJSONArray("daily");

                    double min=0;
                    double max=0;
                    double avg_humid=0;
                    double avg_speed=0;
                    String code;

                    for (int i=0; i<daily.length(); i++){
                        JSONObject day=daily.getJSONObject(i);
                        JSONObject temp=day.getJSONObject("temp");
                        min=temp.getDouble("min");
                        max=temp.getDouble("max");
                        avg_humid=day.getDouble("humidity");
                        avg_speed=day.getDouble("wind_speed");
                        JSONArray weather=day.getJSONArray("weather");
                        JSONObject icon=weather.getJSONObject(0);
                        code=icon.getString("icon");

                        DayTemp obj=new DayTemp();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDateTime date = LocalDateTime.now().plusDays(i);
                            if(i==0){
                                obj.setDate(date, 0);
                            }else {
                                obj.setDate(date, 1);
                            }
                        }else{
                        }



                        obj.setHumid(String.format("%,.1f", avg_humid));
                        obj.setSpeed(String.format("%,.1f", avg_speed));
                        obj.setMin_temp(String.format("%,.1f", min));
                        obj.setMax_temp(String.format("%,.1f", max));
                        IconIm img=new IconIm();
                        obj.setImg(img.get(code));
                        tempDays.add(obj);
                    }
                    rec_adapter=new WeekRecyclerAdapter(inflater.getContext(),tempDays);
                    r_week.setAdapter(rec_adapter);
                    r_week.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

                } catch (JSONException e) {
                    Toast.makeText(context, "ХӨрвүүлэлт дээр алдаа гарж байна!!!!", Toast.LENGTH_SHORT).show();
                }
                System.out.println("gggggggggggggggggggggggg");
                Log.d("response", "xxxxxxxxxxxxxxxxxxxxx"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue reqqueue= Volley.newRequestQueue(context.getApplicationContext());
        reqqueue.add(request);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_week, container, false);
        r_week=(RecyclerView) view.findViewById(R.id.rec_week);
        tempDays=new LinkedList<>();
        this.inflater=inflater;
        return view;
    }
}