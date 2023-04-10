package com.example.wheaterub;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wheaterub.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import models.TimeTemp;
import recycleradapter.RecyclerAdapter;
import weather.IconIm;


public class TodayFragment extends Fragment{



    RecyclerView rview;
    ImageView img;
    Context context;
    DecimalFormat df=new DecimalFormat("#.#");
    TextView temp;
    TextView humidity;
    TextView speed;

    LayoutInflater inflater;
   LinkedList<TimeTemp> list;

    RecyclerAdapter rec_adapter;
    public TodayFragment(Context context) {
        this.context=context;
        String tempUrl="https://api.openweathermap.org/data/2.5/weather?q=Ulaanbaatar,Mongolia&exclude=hourly.temp&appid=570fb075eb15a733d840b089dd35d093";

        String url="https://api.openweathermap.org/data/2.5/forecast?id=524901&lat=47.92&lon=106.91&units=metric&appid=570fb075eb15a733d840b089dd35d093";

        StringRequest req=new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsob=new JSONObject(response);
                    JSONArray arrayweather=jsob.getJSONArray("weather");
                    JSONObject jsonObjectWeather=arrayweather.getJSONObject(0);
                    String code=jsonObjectWeather.getString("icon");
                    IconIm icon=new IconIm();
                    img.setImageResource(icon.get(code));
                    JSONObject jsobmain=jsob.getJSONObject("main");
                    double tempr=jsobmain.getDouble("temp")-273.15;
                    int humiditys=jsobmain.getInt("humidity");
                    JSONObject windobject=jsob.getJSONObject("wind");
                    String wind=windobject.getString("speed");

                    temp.setText(df.format(tempr)+"°C");
                    humidity.setText(humiditys+"%");
                    speed.setText(wind+"mps");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Хүсэлт амжилтгүй боллоо !!!", Toast.LENGTH_SHORT).show();
            }
        });


        StringRequest req_1=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonobject=new JSONObject(response);
                    JSONArray array=jsonobject.getJSONArray("list");



                    for(int i=0;i<9;i++){
                        String formattedDate;
                        LocalDateTime myDateObj = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            myDateObj = LocalDateTime.now().plusHours(3 * i);
                            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:00");
                            formattedDate = myDateObj.format(myFormatObj);



                            JSONObject timp = array.getJSONObject(i);
                            JSONArray weather = timp.getJSONArray("weather");
                            JSONObject jsonObjectWeather = weather.getJSONObject(0);
                            String img_code = jsonObjectWeather.getString("icon");


                            JSONObject timpmain = timp.getJSONObject("main");
                            double timp_tempr = timpmain.getDouble("temp");
                            String date = timp.getString("dt_txt");
                            TimeTemp t = new TimeTemp();
                            IconIm im = new IconIm();
                            t.setPhoto(im.get(img_code));
                            t.setDate(formattedDate);
                            t.setTemp(df.format(timp_tempr));
                            list.add(t);
                        }

                    }
                    rec_adapter=new RecyclerAdapter(inflater.getContext(),list);
                    rview.setAdapter(rec_adapter);
                    rview.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

                } catch (JSONException e) {
                    Toast.makeText(context, "Хүсэлт амжилтгүй боллоо !!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Хүсэлт амжилтгүй боллоо !!!", Toast.LENGTH_SHORT).show();
            }
        });




        RequestQueue reqqueue=Volley.newRequestQueue(context.getApplicationContext());
        reqqueue.add(req_1);
        reqqueue.add(req);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_today, container, false);
        img = (ImageView) view.findViewById(R.id.photo);
        temp=(TextView) view.findViewById(R.id.temp);
        humidity=(TextView) view.findViewById(R.id.hum_per);
        speed=(TextView) view.findViewById(R.id.wind_speed);
        list=new LinkedList<>();

        rview=(RecyclerView) view.findViewById(R.id.rec_today);
        this.inflater=inflater;
        return view;
    }


}