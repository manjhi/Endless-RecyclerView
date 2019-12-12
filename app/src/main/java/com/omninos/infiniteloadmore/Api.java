package com.omninos.infiniteloadmore;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Manjinder Singh on 11 , December , 2019
 */
public interface Api {

//http://infosif.in/tabeebApplication/index.php/api/user/getCountry

//    @FormUrlEncoded
//    @POST("getCountry")
//    Call<MyModel> getCountry(@Field("limit") String limit,
//                             @Field("count") String count);

    //http://infosif.in/tabeebApplication/index.php/api/user/getCountryAll
    @GET("getCountryAll")
    Call<MyModel> getCountry();
}
