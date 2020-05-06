package com.xmj.startfromzero.internet;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoMengjie
 */
public class HttpClientCreate {

    /**
     * Google在Android M(28)取消支持Apache HTTP，若想使用
     * 1、build.gradle文件声明依赖
     *  android {
     *     useLibrary 'org.apache.http.legacy'
     *  }
     * 2、AndroidManifest.xml的application声明
     *  <uses-library android:name="org.apache.http.legacy" android:required="false"/>
     */

    public HttpClient createHttpClient(){
        HttpParams params = new BasicHttpParams();
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(params, 15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(params, 15000);

        HttpConnectionParams.setTcpNoDelay(params, true);

        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(params, true);

        HttpClient httpClient = new DefaultHttpClient(params);

        return httpClient;
    }

    /**
     * get请求
     * @param url
     */
    public void useHttpClientGet(String url){
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Connection", "Keep-Alive");

        HttpClient httpClient = createHttpClient();
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (null != entity){
                InputStream inputStream = entity.getContent();
                String string = convergeStreamToString(inputStream);
                Log.i("========", "请求状态码: " + statusCode + "\n请求结果:\n" + string);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * post请求
     * @param url
     */
    public void useHttpClientPost(String url){
        HttpPost post = new HttpPost(url);
        post.addHeader("Connection", "Keep-Alive");
        HttpClient httpClient = createHttpClient();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("cid", "60"));
        try {
            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (null != entity){
                InputStream inputStream = entity.getContent();
                String string = convergeStreamToString(inputStream);
                Log.i("========", "请求状态码: " + statusCode + "\n请求结果:\n" + string);
                inputStream.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * inputStream转String
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String convergeStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null){
            buffer.append(line + "\n");
        }
        return buffer.toString();
    }
}
