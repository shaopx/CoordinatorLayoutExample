package com.spx.coordinatorlayoutexample.view;


import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;


import com.spx.coordinatorlayoutexample.R;


public class ElemeDetailView extends LinearLayout implements NestedScrollingParent {
    View edv_title, edv_header, edv_content;
    int titleHeight, headerHeight;
    NestedScrollingParentHelper helper = new NestedScrollingParentHelper(this);
    Listener listener;

    public ElemeDetailView(Context context) {
        super(context);
    }

    public ElemeDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        edv_title = findViewById(R.id.edv_title);
        edv_header = findViewById(R.id.edv_header);
        edv_content = findViewById(R.id.edv_content);

        //监听edv_content的位置改变
        //edv_content的移动范围为titleHeight~titleHeight+headerHeight
        //据此算出一个百分比
        edv_content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (listener != null) {
                    float fraction = (edv_content.getY() - titleHeight) / headerHeight;
                    listener.onContentPostionChanged(fraction);
                }
                return true;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        titleHeight = edv_title.getMeasuredHeight();
        headerHeight = edv_header.getMeasuredHeight();
        //重新测量高度
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight() + headerHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onContentPostionChanged(float fraction);
    }

//以下：NestedScrollingParent接口------------------------------

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    //移动edv_content
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        float supposeY = edv_content.getY() - dy;//希望edv_content移动到的位置

        //往上滑,y的边界为titleHeight
        if (dy > 0) {
            if (supposeY >= titleHeight) {
                offset(dy, consumed);
            } else {
                offset((int) (edv_content.getY() - titleHeight), consumed);
            }
        }

        //往下滑,y的边界为titleHeight + headerHeight
        if (dy < 0) {
            if (!ViewCompat.canScrollVertically(target, dy)) {//当target不能向下滑时
                if (supposeY <= titleHeight + headerHeight) {
                    offset(dy, consumed);
                } else {
                    offset((int) (edv_content.getY() - headerHeight - titleHeight), consumed);
                }
            }
        }
    }

    private void offset(int dy, int[] consumed) {
        //第二个参数为正代表向下，为负代表向上
        ViewCompat.offsetTopAndBottom(edv_content, -dy);
        consumed[0] = 0;
        consumed[1] = dy;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

//以下：NestedScrollingParent接口的其他方法

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        helper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        helper.onStopNestedScroll(target);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return helper.getNestedScrollAxes();
    }
}