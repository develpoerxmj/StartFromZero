package com.xmj.startfromzero.internet;

import android.text.TextUtils;

import org.apache.http.NameValuePair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author XiaoMengjie
 */
public class UrlConnectionManager {

    public static HttpURLConnection getHttpURLConnection(String url){
        HttpURLConnection httpURLConnection = null;
        try {
            URL mUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) mUrl.openConnection();
            //连接超时时间
            httpURLConnection.setConnectTimeout(10000);
            //读取超时时间
            httpURLConnection.setReadTimeout(10000);
            //连接方式
            httpURLConnection.setRequestMethod("POST");
            //添加header
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            //接收输入流
            httpURLConnection.setDoInput(true);
            //传参开启输出流
            httpURLConnection.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }

    /**
     * 写入参数
     * @param outputStream
     * @param valuePairList
     * @throws IOException
     */
    public static void postParams(OutputStream outputStream, List<NameValuePair> valuePairList) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (NameValuePair nameValuePair : valuePairList) {
            if (!TextUtils.isEmpty(builder)){
                builder.append("&");
            }
            builder.append(URLEncoder.encode(nameValuePair.getName(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }
}
