package com.xmj.startfromzero.internet.retrofit;

import android.content.Context;
import android.util.Xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author XiaoMengjie
 */
public class RetrofitStudy {

    public void startGet(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create()).build();
        IpService ipService = retrofit.create(IpService.class);
        Call<IpModel> ipMsg = ipService.getIpMsg();
        ipMsg.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                //运行在UI线程
                IpModel body = response.body();
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {

            }
        });


        Call<IpModel> ipMsg1 = ipService.getIpMsg("");

        Call<IpModel> queryMsg = ipService.queryMsg("");

        Call<IpModel> queryMsg1 = ipService.queryMsg(new HashMap<String, String>());

    }

    public void startPost(Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IpService ipService = retrofit.create(IpService.class);

        Call<IpModel> ipModelCall = ipService.postIpMsg("");

        //路径，文件名
        File file = new File(context.getExternalFilesDir("").getAbsolutePath(), "photo.png");
        //文件格式，文件
        RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
        //键，文件名，body
        MultipartBody.Part part = MultipartBody.Part.createFormData("key", "photo.png", body);

        Call<IpModel> photo = ipService.uploadFile(part, RequestBody.create(null, "这是一张图片"));


        Map<String, RequestBody> map = new HashMap<>();
        map.put("file\"; filename=\"" + file.getName(), body);
        map.put("file\"; filename=\"" + file.getName(), body);

        ipService.uploadFiles(map, RequestBody.create(null, "这是一组图片"));
    }

}
