package com.spx.coordinatorlayoutexample.behavior;

import android.content.Context;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 这是从其他博客复制过来的, 缺点是依赖AppBarLayout, 对于不存在AppBarLayout的布局可以使用FooterBehavior.java
 * 原文地址: https://blog.csdn.net/gdutxiaoxu/article/details/53453958
 * 不足: 跟AppBarLayout的高度需要一致, 这个可以通过修改onDependentViewChanged中的值来改进;
 * 必须有一个AppBarLayout
 */

public class FooterBehaviorDependAppBar extends CoordinatorLayout.Behavior<View> {

    public static final String TAG = "xujun";

    public FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //当 dependency instanceof AppBarLayout 返回TRUE，将会调用onDependentViewChanged（）方法
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //根据dependency top值的变化改变 child 的 translationY
        float translationY = Math.abs(dependency.getTop());
        child.setTranslationY(translationY);
        Log.i(TAG, "onDependentViewChanged: " + translationY);
        return true;

    }
}