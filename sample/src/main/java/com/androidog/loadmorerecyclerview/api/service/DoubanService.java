package com.androidog.loadmorerecyclerview.api.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author wpq
 * @version 1.0
 */
public interface DoubanService {

    /**
     * 豆瓣美女api : http://www.dbmeinv.com/dbgroup/show.htm?cid=4&pager_offset=1
     * @param cid cid
     * @param pager_offset 页数
     * @return
     */
    @GET("show.htm")
    Observable<String> getGirls(@Query("cid") String cid, @Query("pager_offset") int pager_offset);

}
