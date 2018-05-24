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

public class JiKeTopicDetailActivity extends BaseActivity {

    private static final String TAG = "JiKeTopicDetail";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jike_topic_detail_layout);



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


}
