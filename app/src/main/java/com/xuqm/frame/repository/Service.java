package com.xuqm.frame.repository;

import com.xuqm.base.model.HttpResult;
import com.xuqm.frame.model.ListPaged;
import com.xuqm.frame.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {


    @GET("wxarticle/list/408/{page}/json")
    Observable<HttpResult<ListPaged<User>>> getList(@Path("page") int page);
}
