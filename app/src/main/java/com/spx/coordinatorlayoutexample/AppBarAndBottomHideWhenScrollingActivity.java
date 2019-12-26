package com.spx.coordinatorlayoutexample;

import android.os.Bundle;
import androidx.annotation.Nullable;

public class AppBarAndBottomHideWhenScrollingActivity  extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.appbar_bottom_hide_activity_layout);
    }
}
