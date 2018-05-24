package com.spx.coordinatorlayoutexample.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.spx.coordinatorlayoutexample.R;
import com.spx.coordinatorlayoutexample.util.VUtil;

public class CustomMyCoordinatorLayout extends LinearLayout implements NestedScrollingParent {
    private static final String TAG = "CustomMyCoord";

    private View headerView;
    private View tabView;
    private ViewPager viewPager;
    private int headerHeight;
    private int statusbarHeight;

    public CustomMyCoordinatorLayout(Context context) {
        super(context);
    }

    public CustomMyCoordinatorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMyCoordinatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = findViewById(R.id.like_appbar_layout);
        tabView = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        statusbarHeight = VUtil.getStatusBarHeight(getContext());
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.w(TAG, "onNestedPreScroll, dy:" + dy + ", target:" + target);
        boolean hiddenTop = dy > 0 && getScrollY() < headerHeight - statusbarHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //不限制顶部的高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = getMeasuredHeight() - tabView.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(),
                headerView.getMeasuredHeight() + tabView.getMeasuredHeight() + viewPager.getMeasuredHeight());

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        headerHeight = headerView.getMeasuredHeight();
    }
}
