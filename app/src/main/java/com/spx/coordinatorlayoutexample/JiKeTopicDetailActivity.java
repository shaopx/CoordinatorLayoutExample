package com.spx.coordinatorlayoutexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.spx.coordinatorlayoutexample.util.VUtil;

import java.util.ArrayList;
import java.util.List;

public class JiKeTopicDetailActivity extends FragmentActivity {

    private static final String TAG = "JiKeTopicDetail";
    List<Fragment> mFragments = new ArrayList<>();

    String[] mTitles = new String[]{
            "精选", "广场"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jike_topic_detail_layout);

        handleStatusBar();


        for (int i = 0; i < mTitles.length; i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }

        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        final View titleTv = findViewById(R.id.title_tv);
        final View toolbarTitleTv = findViewById(R.id.toolbar_title_tv);

        AppBarLayout appBarLayout = findViewById(R.id.smooth_app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int[] locOfTitleImage = new int[2];
                titleTv.getLocationOnScreen(locOfTitleImage);
                int[] locOfTitleToolbar = new int[2];
                toolbarTitleTv.getLocationOnScreen(locOfTitleToolbar);

                int transY = (int) toolbarTitleTv.getTranslationY();
                transY+=(locOfTitleImage[1]-locOfTitleToolbar[1]);
                toolbarTitleTv.setTranslationY(transY);
            }
        });
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
