package com.xmj.startfromzero.internet.okhttp;

import okhttp3.Request;

/**
 * @author XiaoMengjie
 */
public abstract class ResultCallback {

    /**
     * 网络请求成功回调
     * @param string
     */
    public abstract void onSuccess(String string);

    /**
     * 网络请求失败回调
     * @param request
     * @param exception
     */
    public abstract void onError(Request request, Exception exception);
}
