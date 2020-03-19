package com.xmj.startfromzero;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class ImmersiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //沉浸式模式
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_immersive);

        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        /*
            layoutInDisplayCutoutMode的3中属性：
            1：LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 默认属性，竖屏模式下自动延伸到刘海区域，横屏模式下不会延伸到刘海区域
            2：LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 横屏竖屏都会延伸到刘海区域
            3：LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许延伸至刘海区域
         */
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(params);
        }


        /*
            屏内空间偏移安全距离
         */
        final FrameLayout fl = findViewById(R.id.rootLayout);
        final Button topB = findViewById(R.id.top);
        final Button leftB = findViewById(R.id.left);
        if (Build.VERSION.SDK_INT >= 28){
            fl.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @Override
                public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                    DisplayCutout cutout = insets.getDisplayCutout();
                    if (cutout != null){
                        int left = cutout.getSafeInsetLeft();
                        int top = cutout.getSafeInsetTop();
                        int right = cutout.getSafeInsetRight();
                        int bottom = cutout.getSafeInsetBottom();

                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                            //竖屏
                            FrameLayout.LayoutParams topParams = (FrameLayout.LayoutParams) topB.getLayoutParams();
                            topParams.setMargins(left, top, right, bottom);
                        }else {
                            //横屏
                            FrameLayout.LayoutParams leftParams = (FrameLayout.LayoutParams) leftB.getLayoutParams();
                            leftParams.setMargins(left, top, right, bottom);
                        }
                    }
                    return insets.consumeSystemWindowInsets();
                }
            });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
