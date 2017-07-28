package com.lemuel.ganamrs.api;

import com.lemuel.ganamrs.entity.GankBean;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.entity.GankResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankService {

    @GET("api/data/{type}/{pageSize}/{page}")
    Flowable<GankResponse<GankBean>> getGank(@Path("type") String type, @Path("pageSize") int pageSize, @Path("page") int page);

    @GET("api/random/data/福利/4")
    Flowable<GankResponse<GankGril>> getRandomGrils();

    @GET("api/random/data/福利/1")
    Flowable<GankResponse<GankGril>> getRandomGril();

}
