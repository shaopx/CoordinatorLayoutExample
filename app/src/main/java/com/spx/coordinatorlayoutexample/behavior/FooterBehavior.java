package com.spx.coordinatorlayoutexample.behavior;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * 向上滑动则隐藏底部view, 如果滑到底部, 则又显示
 * 如果向下滑动则显示
 * 目前仅支持recyclerview, 和NestedScrollView
 */
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final String TAG = "FooterBehavior";

    private static final int ANIMATION_DURATION = 220;
    private static final int MSG_HIDE = 1;
    private static final int MSG_SHOW = 2;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_HIDE:
                    hide((View) msg.obj);
                    break;

                case MSG_SHOW:
                    show((View) msg.obj);
                    break;
            }
            return false;
        }
    });

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //1.判断滑动的方向 我们需要垂直滑动
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes, int type) {
        if ((nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0) {
            if (reachBottom(target)) {
                return false;
            }
            clearMsg();
            return true;
        }
        return false;
    }


    //向上滑动则隐藏,  如果滑动到底部则显示
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child,
                                  View target, int dx, int dy, int[] consumed, int type) {
        float translationY = child.getTranslationY();

        clearMsg();

        if ((dy > 0 && translationY >= child.getHeight()) || (dy < 0 && translationY <= 0)) {

        } else {
            if (dy > 0) {
                translationY = Math.min(child.getHeight(), translationY + dy);
            } else {
                translationY = Math.max(0, translationY + dy);
            }

            child.setTranslationY(translationY);
        }
    }


    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
        clearMsg();

        if (child.getTranslationY() < child.getHeight() / 2f || reachBottom(target)) {
            Message msg = Message.obtain();
            msg.what = MSG_SHOW;
            msg.obj = child;
            handler.sendMessageDelayed(msg, 200);
        } else {
            Message msg = Message.obtain();
            msg.what = MSG_HIDE;
            msg.obj = child;
            handler.sendMessageDelayed(msg, 200);
        }
    }

    private boolean reachBottom(View target) {
        if (target instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) target;
            int scrollY = nestedScrollView.getScrollY();
            View onlyChild = nestedScrollView.getChildAt(0);
            if (onlyChild.getHeight() <= scrollY + nestedScrollView.getHeight()) {
                return true;
            }
        } else if (target instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) target;
            if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                    >= recyclerView.computeVerticalScrollRange()) {
                return true;
            }
        }
        return false;
    }


    private void clearMsg() {
        handler.removeMessages(MSG_SHOW);
        handler.removeMessages(MSG_HIDE);
    }

    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).
                setInterpolator(INTERPOLATOR).setDuration(ANIMATION_DURATION);
        animator.start();
    }

    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).
                setInterpolator(INTERPOLATOR).
                setDuration(ANIMATION_DURATION);
        animator.start();

    }
}