package com.spx.coordinatorlayoutexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Context context;

    private RecyclerView recyclerView;
    private List<SampleItem> items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        setContentView(R.layout.main_activity);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new SampleAdapter());

        initSampleItems();
    }

    private void initSampleItems() {
        items.add(new SampleItem("谷歌示例", ScrollingActivity.class));
        items.add(new SampleItem("即刻话题详情页", JiKeTopicDetailActivity.class));
        items.add(new SampleItem("自定义NestedScrollingParent和NestedScrollingChild", CustomNestedScrolling.class));
        items.add(new SampleItem("自定义NestedScrollingParent实现折叠嵌套滑动效果", CustomFoldingScrolling.class));
        items.add(new SampleItem("自定义一个类似CoordinatorLayout的布局", CustomMyCoorLayoutActivity.class));
    }

    private class SampleItem {
        String title;
        Class classz;

        public SampleItem(String title, Class classz) {
            this.title = title;
            this.classz = classz;
        }
    }

    private class SampleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;

        public SampleViewHolder(View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_tv);
        }
    }

    private class SampleAdapter extends RecyclerView.Adapter<SampleViewHolder> {

        @NonNull
        @Override
        public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_item_layout, parent, false);
            return new SampleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {
            final SampleItem sampleItem = items.get(position);
            holder.titleTv.setText(sampleItem.title);
            holder.titleTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, sampleItem.classz);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
