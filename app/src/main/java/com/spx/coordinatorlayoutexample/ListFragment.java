package com.spx.coordinatorlayoutexample;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spx.coordinatorlayoutexample.adapter.SampleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private Context context;
    private List<String> data = new ArrayList<>();
    private String title;

    public static ListFragment newInstance(String title) {
        ListFragment listFragment = new ListFragment();
        listFragment.title = title;
        return listFragment;
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(context);
        context = c;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);

        for (int i = 0; i < 50; i++) {
            data.add(title + "第" + i + "个数据");
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new SampleAdapter(data));

        return view;
    }

}
