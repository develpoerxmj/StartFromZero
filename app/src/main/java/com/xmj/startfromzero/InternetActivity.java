package com.xmj.startfromzero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.xmj.startfromzero.internet.HttpClientCreate;

/**
 * @author XiaoMengjie
 */
public class InternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
    }

    public void get(View view) {
        final HttpClientCreate httpCreate = new HttpClientCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpCreate.useHttpClientGet("https://www.wanandroid.com/article/list/0/json");
            }
        }).start();
    }
}
