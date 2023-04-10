package recycleradapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheaterub.R;

import java.util.LinkedList;

import models.TimeTemp;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private final LinkedList<TimeTemp>  List;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView date;
        TextView temp;
        public ViewHolder(View item, RecyclerAdapter recyclerAdapter) {
            super(item);
            img=item.findViewById(R.id.imgs);
            date=item.findViewById(R.id.time);
            temp=item.findViewById(R.id.time_temp);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public RecyclerAdapter(Context context, LinkedList<TimeTemp> list_time_date) {
        mInflater = LayoutInflater.from(context);
        this.List = list_time_date;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item=mInflater.inflate(R.layout.today_list,parent,false);
        return new ViewHolder(item,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimeTemp current=List.get(position);
        holder.img.setImageResource(current.getPhoto());
        holder.temp.setText(current.getTemp());
        holder.date.setText(current.getDate());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }




}
