package com.spx.coordinatorlayoutexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.spx.coordinatorlayoutexample.util.VUtil;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends FragmentActivity {


    protected List<Fragment> mFragments = new ArrayList<>();

    protected  String[] mTitles = new String[]{
            "精选", "广场"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleStatusBar();


        for (int i = 0; i < mTitles.length; i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }
    }

    // 统一处理透明状态栏, 目前只有首页在用
    protected void handleStatusBar(){
        View statusBarHolderView = findViewById(R.id.statusbar_holder);
        if (statusBarHolderView!=null ) {
            statusBarHolderView.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = statusBarHolderView.getLayoutParams();
            layoutParams.height = VUtil.getStatusBarHeight(this);
            statusBarHolderView.setLayoutParams(layoutParams);
        }
    }
}
