package com.xmj.startfromzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳往沉浸式activity
     * @param view
     */
    public void immersive(View view) {
        Intent intent = new Intent(this, ImmersiveActivity.class);
        startActivity(intent);
    }
}
/**
 * 运行text方法：
 * 首先执行到1，进入循环执行2，此时如果
 * break----跳出循环，执行3
 * continue----不在执行本次循环，从新开始循环，执行2
 * return----结束执行text方法
 *
 * text(){
 *    //1
 *    for(;;){
 *        //2
 *    }
 *    //3
 * }
 */
