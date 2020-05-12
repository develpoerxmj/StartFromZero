package com.xmj.startfromzero.internet.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.apache.http.client.utils.URIUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoMengjie
 */
public class VolleyStudy {

    private static final String TAG = "TAG";
    private Context context;

    public VolleyStudy(Context context) {
        this.context = context;
    }

    public void start(String url){
        RequestQueue queue = Volley.newRequestQueue(context);

        Request request = createStringRequest(url);
        queue.add(request);

        /**
         * ImageLoader请求图片
         * 1、创建RequestQueue=Volley.newRequestQueue()
         * 2、创建ImageLoader(queue, cache)
         * 3、创建Listener=ImageLoader.getImageListener(imageView, resID. resID)
         * 4、imageLoader.get(url, listener)
         */
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        imageLoader.get(url, ImageLoader.getImageListener(null, 0, 0));
    }

    /**
     * 创建返回类型为String的请求
     * @return
     */
    private StringRequest createStringRequest(String url){
        return new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //这里添加请求需要传递的参数
                return super.getParams();
            }
        };
    }

    /**
     * 创建返回类型为Json的请求
     * @param url
     * @return
     */
    private JsonRequest createJsonRequest(String url){
        //传递参数
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject(params);

        return new JsonObjectRequest(JsonRequest.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //利用Gson解析成相应的对象
                new Gson().toJson(response.toString(), String.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
    }

    /**
     * 创建返回类型为Bitmap的请求
     * @param url
     * @return
     */
    private ImageRequest createImageRequest(String url){
        return new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

            }
        }, 0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });
    }
}
