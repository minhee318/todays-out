package com.happiness.todaysout.src.login.Interface;

import com.happiness.todaysout.src.login.models.AccessToken;
import com.happiness.todaysout.src.login.models.LoginData;
import com.happiness.todaysout.src.login.models.LoginResponse;
import com.happiness.todaysout.src.login.models.SignInResponse;
import com.happiness.todaysout.src.login.models.SplashResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {

    @POST("/users/login/kakao")
    Call<LoginResponse> postKakaoLogin(@Body AccessToken token);

    @POST("/users")
    Call<SignInResponse> postSignIn(@Body LoginData loginData);

    @GET("/users/jwt")
    Call<SplashResponse> getAutoLogin();


//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<DefaultResponse> postTest(@Body RequestBody params);
}
