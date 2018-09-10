package com.spx.coordinatorlayoutexample.behavior;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 *
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
            clearMsg();
            return true;
        }
        return false;
    }


    //2.根据滑动的距离显示和隐藏footer view
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child,
                                  View target, int dx, int dy, int[] consumed, int type) {
        float translationY = child.getTranslationY();
        Log.d(TAG, "onNestedPreScroll: ...dy:" + dy + ", translationY:" + translationY);

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
        Log.d(TAG, "onStopNestedScroll: ...");

        clearMsg();

        if (child.getTranslationY() < child.getHeight() / 2f) {
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

//    @Override
//    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
//        Log.d(TAG, "onNestedPreFling: ...");
//        handler.removeMessages(MSG_SHOW);
//        handler.removeMessages(MSG_HIDE);
//        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
//    }

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