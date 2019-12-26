package com.spx.coordinatorlayoutexample.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseFragmentAdapter extends FragmentPagerAdapter {

    protected List<Fragment> mFragmentList;

    protected String[] mTitles;

    public BaseFragmentAdapter(FragmentManager fm) {
        this(fm, null, null);
    }

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] mTitles) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        this.mFragmentList = fragmentList;
        this.mTitles = mTitles;
    }


    @Override
    public Fragment getItem(int position) {
        return isEmpty() ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return isEmpty() ? 0 : mFragmentList.size();
    }

    public boolean isEmpty() {
        return mFragmentList == null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
