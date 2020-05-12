package com.xmj.startfromzero.internet.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author XiaoMengjie
 */
public class OkHttpEngine {

    private static volatile OkHttpEngine mInstance;
    private OkHttpClient client;
    private Handler handler;

    private OkHttpEngine(Context context) {
        File file = new File(context.getExternalCacheDir().getAbsolutePath());
        Cache cache = new Cache(file, 10 * 1024 * 1024);
        client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .cache(cache)
                .build();
        handler = new Handler();
    }

    /**
     * 单例
     * @param context
     * @return
     */
    public static OkHttpEngine getInstance(Context context){
        if (mInstance == null){
            synchronized (OkHttpEngine.class){
                if (mInstance == null){
                    mInstance = new OkHttpEngine(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 异步网络请求
     * @param url
     */
    private void getAsyncRequest(String url, ResultCallback callback){
        Request request = new Request.Builder()
                .url(url).build();
        Call call = client.newCall(request);
        dealCall(call, callback);
    }

    /**
     * 处理Call
     * @param call
     * @param callback
     */
    private void dealCall(Call call, final ResultCallback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                sendFailureCallback(call.request(), e, callback);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                sendSuccessCallback(response.body().string(), callback);
            }
        });
    }

    /**
     * 发送至主线程
     * @param body
     * @param callback
     */
    private void sendSuccessCallback(final String body, final ResultCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onSuccess(body);
                }
            }
        });
    }

    /**
     * 发送至主线程
     * @param request
     * @param e
     * @param callback
     */
    private void sendFailureCallback(final Request request, final IOException e, final ResultCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onError(request, e);
                }
            }
        });
    }
}
