package com.xuqm.frame.repository;

import com.xuqm.base.model.HttpResult;
import com.xuqm.frame.model.AD;
import com.xuqm.frame.model.Article;
import com.xuqm.frame.model.ListPaged;
import com.xuqm.frame.model.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {


    @GET("wxarticle/list/408/{page}/json")
    Observable<HttpResult<ListPaged<User>>> getList(@Path("page") int page);

    @GET("banner/json")
    Observable<HttpResult<ArrayList<AD>>> getAd();

    @GET("article/list/{page}/json")
    Observable<HttpResult<ListPaged<Article>>> getHome(@Path("page") int page);

}
