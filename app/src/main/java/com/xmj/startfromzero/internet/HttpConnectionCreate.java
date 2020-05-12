package com.xmj.startfromzero.internet;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
/**
 * @author XiaoMengjie
 */
public class HttpConnectionCreate {

    public void useHttpURLConnectionGet(String url){
        //get如果需要传递参数，则需要将其拼接到url尾部
        //格式为url?param1=xxx&param2=xxx&[…]
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                String string = convertStreamToString(inputStream);
                Log.i("TAG", string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useHttpURLConnectionPost(String url){
        //post传递参数，是从连接中得到输出流，通过输出流写入服务器
        //一般参数格式：param1=xxx&param2=xxx&[…]

        //json参数格式：{param1:xxx,param2:xxx,...},需要设置类型httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8")
        /**
         * 上传文件
         * 1、设置类型connection.setRequestProperty("Content-Type", "file/*")
         * 2、
         */
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("POST");

            //允许读入
            httpURLConnection.setDoInput(true);
            //允许写
            httpURLConnection.setDoOutput(true);
            //不使用缓存
            httpURLConnection.setUseCaches(false);
            //设置参数类型的json格式
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            //连接
            httpURLConnection.connect();

            //传递参数
            String values = "name=123&password=123456";
            String valueJson = "{name:123,password:123456}";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
            writer.write(values);
            writer.close();

            //上传文件
            FileInputStream fileInputStream = new FileInputStream("file");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            byte[] bytes = new byte[1024];
            int length = -1;
            while ((length = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, length);
            }
            fileInputStream.close();
            outputStream.close();


            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                String string = convertStreamToString(inputStream);
                Log.i("TAG", string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }
}
