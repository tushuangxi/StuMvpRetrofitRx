package com.senon.mvpretrofitrx.content.libding.http;

import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseApi;

/**
 *
 */

public class Api {

//    private String baseUrl = "http://www.kuaidi100.com/";
//    private String baseUrl = "https://www.wanandroid.com/";
    private String baseUrl = "http://api.zhuishushenqi.com/";


    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }
}
