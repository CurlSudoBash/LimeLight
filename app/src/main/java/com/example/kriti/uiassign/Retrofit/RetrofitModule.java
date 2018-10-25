package com.example.kriti.uiassign.Retrofit;


import android.util.Log;

import com.example.kriti.uiassign.Utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitModule {

    public static void synchronize() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.25.17.232:3000/")
                .build();

        API api = retrofit.create(API.class);
        String payload = Utils.mapToPayload(Utils.locationMap);
        RequestBody requestBody =  RequestBody.create(MediaType.parse("text/plain"), payload);
        api.postUser(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.body() == null) return;
                    Log.d("RetrofitExample", response.body().string());
                    Utils.updateMap(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}
