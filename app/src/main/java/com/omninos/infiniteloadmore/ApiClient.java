package com.omninos.infiniteloadmore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Manjinder Singh on 11 , December , 2019
 */
public class ApiClient {


    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    private static Retrofit retrofit = null;


    public <T extends Object> T PlacesBuild(Class<T> type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://infosif.in/tabeebApplication/index.php/api/user/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(type);
    }
}
