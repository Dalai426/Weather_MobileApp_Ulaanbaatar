package recycleradapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheaterub.R;

import java.time.temporal.WeekFields;
import java.util.LinkedList;

import models.DayTemp;


public class WeekRecyclerAdapter extends RecyclerView.Adapter<WeekRecyclerAdapter.ViewHolder> {


    private final LayoutInflater inflater;
    private final LinkedList<DayTemp> list;

    public WeekRecyclerAdapter(Context context, LinkedList<DayTemp> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }


    @NonNull
    @Override
    public WeekRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item=inflater.inflate(R.layout.week_list,parent,false);
        return new ViewHolder(item,this);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekRecyclerAdapter.ViewHolder holder, int position) {
        DayTemp current=list.get(position);
        holder.day.setText(current.getDate());
        holder.min.setText(current.getMin_temp());
        holder.max.setText(current.getMax_temp());
        holder.humid.setText(current.getHumid());
        holder.speed.setText(current.getSpeed());
        holder.img.setImageResource(current.getImg());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        TextView min;
        TextView max;
        TextView humid;
        TextView speed;
        ImageView img;
        public ViewHolder(View view, WeekRecyclerAdapter adapter) {
            super(view);
            day=view.findViewById(R.id.day);
            min=view.findViewById(R.id.min);
            max=view.findViewById(R.id.max);
            humid=view.findViewById(R.id.humid);
            speed=view.findViewById(R.id.speed);
            img=view.findViewById(R.id.imageView);
        }
    }



}