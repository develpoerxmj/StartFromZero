package com.xmj.startfromzero.internet.okhttp;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author XiaoMengjie
 */
public class OkHttpStudy {

    /**
     * 异步请求：enqueue(request) onResponse回调在子线程
     * 同步请求：execute() 需要开启子线程请求
     *
     *OkHttp的使用
     *  1、创建OkHttpClient对象client = new OkHttpClient()
     *      OkHttp3中使用new OkHttpClient.Builder().builder()创建
     *
     *  2、创建Request对象，request = new Request.Builder().url().build
     *      默认GET
     *  3、call = client.newCall(request)
     *  4、call.execute() 同步请求，或call.enqueue(CallBack) 异步请求
     */
    public void getRequest() throws IOException {
        Request.Builder builder = new Request.Builder()
                .url("https://www.baidu.com");
        Request request = builder.build();
        OkHttpClient client = new OkHttpClient();
        //异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //并非在UI线程
            }
        });
        //同步请求
//        Response response = client.newCall(request).execute();
    }

    public void postRequest() throws IOException {
        //用FormBody封装请求参数
        RequestBody formBody = new FormBody.Builder().add("", "").build();

        Request request = new Request.Builder().url("https://www.baidu.com")
                .post(formBody).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

//        Response response = client.newCall(request).execute();
    }

    /**
     * 上传文件，即post请求
     * @param context
     */
    public void uploadFile(Context context){
        String filePath = "";
        //sd状态为MEDIA_MOUNTED才是可读可写
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filePath = context.getExternalFilesDir("").getAbsolutePath();
        }else {
            return;
        }
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        File file = new File(filePath, "wangshu.txt");
        Request request = new Request.Builder().url("https://www,baidu.com")
                .post(RequestBody.create(mediaType, file)).build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 下载图片
     * @param url
     * @param context
     */
    public void downloadPicture(String url, final Context context){
        final Request request = new Request.Builder().url(url)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //response转为输入流
                InputStream inputStream = response.body().byteStream();
                FileOutputStream outputStream = null;
                String filePath = "";
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
                    //外部存储可读写
                    filePath = context.getExternalFilesDir("").getAbsolutePath();
                }else {
                    //不可读写放入内存
                    filePath = context.getFilesDir().getAbsolutePath();
                }
                File file = new File(filePath, "picture.png");
                if (null != file){
                    outputStream = new FileOutputStream(file);
                    byte[] bytes = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(bytes)) != -1){//读到数据
                        outputStream.write(bytes, 0, len);
                    }
                    outputStream.flush();
                }
            }
        });
    }

    /**
     * 上传文件和其他类型字段
     * @param url
     */
    public void uploadMultipart(String url){
        MediaType mediaType = MediaType.parse("image/png");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "value")
                .addFormDataPart("key", "file", RequestBody.create(mediaType, new File("")))
                .build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID" + "")
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 创建OkHttpClient对象，设置超时时间和缓存
     * @return
     */
    public OkHttpClient createOkHttpClient(Context context){
        OkHttpClient client = new OkHttpClient.Builder()
                //连接超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                //写入超时时间
                .writeTimeout(20, TimeUnit.SECONDS)
                //读取超时时间
                .readTimeout(20, TimeUnit.SECONDS)
                //缓存目录
                .cache(new Cache(new File(context.getExternalCacheDir().getAbsolutePath()), 10 * 1024 * 1024))
                .build();
        return client;
    }


}
