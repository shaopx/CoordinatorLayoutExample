package com.spx.coordinatorlayoutexample.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.spx.coordinatorlayoutexample.R;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTv;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        titleTv = itemView.findViewById(R.id.title_tv);
    }
}
