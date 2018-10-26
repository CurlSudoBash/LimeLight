package com.microsoft.cfd.limelight.Retrofit;


import android.util.Log;

import com.microsoft.cfd.limelight.EventAdapter;
import com.microsoft.cfd.limelight.Utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitModule {

    public static String BaseURL = "http://172.30.26.23:3000/";

    public static void synchronize() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .build();

        API api = retrofit.create(API.class);
        String payload = Utils.mapToPayload(Utils.locationMap);
        RequestBody requestBody =  RequestBody.create(MediaType.parse("text/plain"), payload);
        api.syncMap(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.body() == null) return;
                    String res = response.body().string();
                    Log.d("RetrofitExample", res);
                    Utils.updateMap(res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public static void createEvent(String eventName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .build();

        API api = retrofit.create(API.class);
        String payload = eventName + "|" + Utils.location;
        RequestBody requestBody =  RequestBody.create(MediaType.parse("text/plain"), payload);
        api.createEvent(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.body() == null) return;
                    String res = response.body().string();
                    Utils.updateEvents(res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public static void fetchEvents() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .build();

        API api = retrofit.create(API.class);
        api.getEvents().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response.body() == null) return;
                    String res = response.body().string();
                    Utils.updateEvents(res);
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
