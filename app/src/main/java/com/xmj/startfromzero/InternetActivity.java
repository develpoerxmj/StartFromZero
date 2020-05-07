package com.xmj.startfromzero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xmj.startfromzero.internet.HttpClientCreate;
import com.xmj.startfromzero.internet.HttpConnectionCreate;
import com.xmj.startfromzero.internet.UrlConnectionManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author XiaoMengjie
 */
public class InternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        if (BuildConfig.DEBUG){
            handleSSLHandshake();
        }
    }

    public void get(View view) {
        new Thread(new InternetThread()).start();
    }

    public class InternetThread implements Runnable{
        @Override
        public void run() {
//            HttpClientCreate httpCreate = new HttpClientCreate();
//            httpCreate.useHttpClientGet("https://www.wanandroid.com/article/list/0/json");
//            httpCreate.useHttpClientGet("https://www.baidu.com");

//            new HttpConnectionCreate().useHttpURLConnectionGet("https://www.baidu.com");
            useHttpURLConnectionPost("https://www.baidu.com");
        }
    }


    private void useHttpURLConnectionPost(String url){
        try {
            HttpURLConnection connection = UrlConnectionManager.getHttpURLConnection(url);
            //得到输出流
            OutputStream outputStream = connection.getOutputStream();
            //获得参数
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("", ""));
            //传递参数
            UrlConnectionManager.postParams(outputStream, list);
            //连接
            connection.connect();
            //对输入流做相应的解析，这里转为String
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                builder.append(line + "\n");
            }
            reader.close();

            String response = builder.toString();
            Log.i("TAG", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 信任所有证书
     */
    public void handleSSLHandshake() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            //trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }
}
