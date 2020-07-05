package com.example.volgatech;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volgatech.Interface.ItemClickListener;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<RecordViewHolder>{
    private List<Area> listData;
    private RecordActivity record;

    public AreaAdapter(List<Area> listData, RecordActivity record) {
        this.listData = listData;
        this.record = record;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(record);
        View itemView = inflater.inflate(R.layout.item_record,parent,false);
        return new RecordViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecordViewHolder holder, int position) {

        holder.txt_record_item_date.setText(listData.get(position).getDate());

        final Area area = getItem(position);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Area.class.getSimpleName(), area);
                context.startActivity(intent);
            }
        });

        holder.resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Result2Activity.class);
                intent.putExtra(Area.class.getSimpleName(), area);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public Area getItem(int position){
        return listData.get(position);
    }

    public void removeItem(int position){
        listData.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Area item, int position){
        listData.add(position, item);
        notifyItemInserted(position);
    }
}
