package com.example.volgatech;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.volgatech.Interface.ItemClickListener;

public class RecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_record_item_date;
    public Button resultButton;

    private ItemClickListener itemClickListener;

    public RelativeLayout view_background;
    public LinearLayout view_foreground;

    public RecordViewHolder(View itemView) {
        super(itemView);

        txt_record_item_date = itemView.findViewById(R.id.record_item_date);
        resultButton = itemView.findViewById(R.id.result_bt);

        view_background = itemView.findViewById(R.id.view_background);
        view_foreground = itemView.findViewById(R.id.view_foreground);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}