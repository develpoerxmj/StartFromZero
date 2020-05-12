package com.xmj.startfromzero.internet.retrofit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author XiaoMengjie
 */
public interface IpService {

    /**
     * GRE请求访问
     * @return
     */
    @GET("")
    Call<IpModel> getIpMsg();

    /**
     * 动态配置URL
     * @param path
     * @return
     */
    @GET("{path}/、、、、")
    Call<IpModel> getIpMsg(@Path("path") String path);


    /**
     * 动态指定查询条件
     * @param query
     * @return
     */
    @GET("")
    Call<IpModel> queryMsg(@Query("query")String query);

    /**
     * 动态指定查询条件组
     * @param map
     * @return
     */
    @GET("")
    Call<IpModel> queryMsg(@QueryMap Map<String, String> map);


    /**
     * 传输数据类型为键值对
     * FormUrlEncoded表面是个表单请求
     * @param ip
     * @return
     */
    @FormUrlEncoded
    @POST("")
    Call<IpModel> postIpMsg(@Field("ip")String ip);

    /**
     * 传输数据类型为JSON字符串
     *  Retrofit会自动将Ip对象转为字符串
     * @param ip
     * @return
     */
    @POST("")
    Call<IpModel> postIpMsg(@Body Ip ip);

    /**
     * 单个文件上传
     * @param photo 上传的文件
     * @param body 对文件的描述
     * @return
     */
    @Multipart
    @POST("")
    Call<IpModel> uploadFile(@Part MultipartBody.Part photo, @Part("description") RequestBody body);

    /**
     * 多个文件上传
     * @param map 上传的文件集合
     * @param body 对文件的描述
     * @return
     */
    @Multipart
    @POST("")
    Call<IpModel> uploadFiles(@PartMap Map<String, RequestBody> map, @Part("description") RequestBody body);


    /**
     * 静态添加头部
     * 多个注解用{}包含
     * @return
     */
    @GET("")
    @Headers({"Accept-Encoding: application/json",
            "User-Agent: MoonRetrofit"})
    Call<IpModel> addHeader();

    /**
     * 动态添加头部
     * @param location
     * @return
     */
    @GET("")
    Call<String> addHeader(@Header("Location") String location);
}
