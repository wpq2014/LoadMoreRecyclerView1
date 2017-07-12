package com.androidog.loadmorerecyclerview.api;

import com.androidog.loadmorerecyclerview.api.service.DoubanService;
import com.androidog.loadmorerecyclerview.api.service.GankService;

public class Api {

    private GankService mGankService;
    private DoubanService mDoubanService;

    private Api() {}

    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

//    public <T> T getService(Class<T> classType) {
//        return RetrofitManager.getInstance().getGankRetrofit().create(classType);
//    }

    public GankService getGankService() {
        if (mGankService == null) {
            mGankService = RetrofitManager.getInstance().getGankRetrofit().create(GankService.class);
        }
        return mGankService;
    }

    public DoubanService getDoubanService() {
        if (mDoubanService == null) {
            mDoubanService = RetrofitManager.getInstance().getDoubanRetrofit().create(DoubanService.class);
        }
        return mDoubanService;
    }
}
