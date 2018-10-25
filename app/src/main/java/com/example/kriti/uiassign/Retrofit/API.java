package com.example.kriti.uiassign.Retrofit;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;


public interface API {
    @POST("/update")
    Call<ResponseBody> postUser(@Body RequestBody requestBody);
}
