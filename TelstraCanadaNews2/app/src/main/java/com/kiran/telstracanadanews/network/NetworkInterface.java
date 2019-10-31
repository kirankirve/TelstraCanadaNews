package com.kiran.telstracanadanews.network;

import com.kiran.telstracanadanews.models.CanadaNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NetworkInterface {
    @GET("facts")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<CanadaNews> fetchNewsRequestApi();
}
