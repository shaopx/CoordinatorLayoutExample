package com.spx.coordinatorlayoutexample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spx.coordinatorlayoutexample.R;

import java.util.ArrayList;
import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private List<String> data = new ArrayList<>();
    public SampleAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        final String item = data.get(position);
        holder.titleTv.setText(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}