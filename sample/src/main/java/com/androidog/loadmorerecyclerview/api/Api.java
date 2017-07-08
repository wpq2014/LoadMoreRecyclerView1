package com.androidog.loadmorerecyclerview.api;

import com.androidog.loadmorerecyclerview.api.service.DoubanService;
import com.androidog.loadmorerecyclerview.api.service.GankService;

public class Api {

    private Api() { throw new AssertionError("cannot be instantiated");}

    public static GankService createGankService() {
        return RetrofitManager.getInstance().getGankRetrofit().create(GankService.class);
    }

    public static DoubanService createDoubanService() {
        return RetrofitManager.getInstance().getDoubanRetrofit().create(DoubanService.class);
    }
}
