package com.androidog.loadmorerecyclerview.api.service;

import com.androidog.loadmorerecyclerview.bean.GanHuo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author wpq
 * @version 1.0
 */
public interface GankService {

    /**
     * gank.io福利api : http://gank.io/api/data/福利/{每页条数}/页数
     * @param type 福利
     * @param count 每页条数
     * @param page 页数
     * @return
     */
    @GET("api/data/{type}/{count}/{page}")
    Observable<GanHuo> getGanHuo(@Path("type") String type, @Path("count") int count, @Path("page") int page);

}
