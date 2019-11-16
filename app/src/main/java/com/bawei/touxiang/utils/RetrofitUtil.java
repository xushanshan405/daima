package com.bawei.touxiang.utils;

import com.bawei.touxiang.api.ApiServer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * FileName: RetrofitUtil
 * Author: 徐姗姗
 * Date: 2019/11/16 12:26
 */
public class RetrofitUtil {
    public static ApiServer apiServer;
    private HttpLoggingInterceptor httpLoggingInterceptor;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public RetrofitUtil() {
    }

    public static ApiServer getApiServer() {
        if (apiServer == null) {
            synchronized (RetrofitUtil.class) {
                if (apiServer == null) {
                    apiServer = new RetrofitUtil().getRetrofit();
                }
            }
        }
        return apiServer;
    }

    private ApiServer getRetrofit() {
        ApiServer apiServer = initRetrofit(initOk()).create(ApiServer.class);
        return apiServer;
    }
    private OkHttpClient initOk() {
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {

                    private Response response;
                    private Request request;

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        request = chain.request();
                        response = chain.proceed(request);
                        return response;
                    }
                })
                .retryOnConnectionFailure(true)
                .build();
        return okHttpClient;
    }
    private Retrofit initRetrofit(OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://172.17.8.100/techApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
