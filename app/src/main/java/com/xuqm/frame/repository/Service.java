package com.xuqm.frame.repository;

import com.xuqm.base.model.HttpResult;
import com.xuqm.frame.model.AD;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Service {


    @GET("banner/json")
    Observable<HttpResult<ArrayList<AD>>> getAd();
}
