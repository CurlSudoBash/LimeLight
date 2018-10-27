package com.microsoft.cfd.limelight.Retrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface API {

    @POST("/update")
    Call<ResponseBody> syncMap(@Body RequestBody requestBody);

    @GET("/events")
    Call<ResponseBody> getEvents();

    @POST("/events")
    Call<ResponseBody> createEvent(@Body RequestBody requestBody);

    @GET("/cluster")
    Call<ResponseBody> fetchClusterInfo(@Query("id") String clusterId);

    @POST("/cluster")
    Call<ResponseBody> updateCluster(@Body RequestBody requestBody);
}
