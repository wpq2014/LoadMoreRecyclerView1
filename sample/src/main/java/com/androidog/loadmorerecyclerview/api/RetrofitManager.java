package com.androidog.loadmorerecyclerview.api;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author wpq
 * @version 1.0
 */
public class RetrofitManager {

    // gank.io
    private static final String BASE_URL_GANK = "http://gank.io/";
    // 豆瓣
    private static final String BASE_URL_DOUBAN = "http://www.dbmeinv.com/dbgroup/";
    // Okhttp默认超时
    private static final int DEFAULT_TIMEOUT = 10;

    private HashMap<String, Retrofit> mRetrofitMap = new HashMap<>();

    private RetrofitManager() {}

    static RetrofitManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    Retrofit getGankRetrofit() {
        return getRetrofit(BASE_URL_GANK, GsonConverterFactory.create());
    }

    Retrofit getDoubanRetrofit() {
        return getRetrofit(BASE_URL_DOUBAN, ScalarsConverterFactory.create());
    }

    private Retrofit getRetrofit(String baseUrl, Converter.Factory converterFactory) {
        Retrofit retrofit = mRetrofitMap.get(baseUrl);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(createOkhttpClient())
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mRetrofitMap.put(baseUrl, retrofit);
        }
        return retrofit;
    }

    private OkHttpClient createOkhttpClient() {
        //配置超时拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //配置log打印拦截器
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }
}
