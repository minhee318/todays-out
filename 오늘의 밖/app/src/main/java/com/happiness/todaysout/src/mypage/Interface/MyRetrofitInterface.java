package com.happiness.todaysout.src.mypage.Interface;


import com.happiness.todaysout.src.mypage.model.MyDongResponse;
import com.happiness.todaysout.src.mypage.model.MyResponse;
import com.happiness.todaysout.src.mypage.model.PatchInfo;
import com.happiness.todaysout.src.mypage.model.PatchProfileResponse;
import com.happiness.todaysout.src.weather.models.RegisterInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyRetrofitInterface {


    @GET("/users/{userIdx}")
    Call<MyResponse> getMy(@Path("userIdx") Long userIdx);

    @DELETE("/users/jwt")
    Call<ReportResponse> deleteBye();

    @PATCH("/users/jwt")
    Call<PatchProfileResponse> patchProfile(@Body PatchInfo patchInfo);

    @GET("/address/{addressIdx}/thirdAddressNameList")
    Call<MyDongResponse> getMyDong(@Path("addressIdx") Long addressIdx);

    @GET("/address/{addressIdx}/thirdAddressNameList")
    Call<MyDongResponse> getMyDong2(@Path("addressIdx") Long addressIdx);


    @POST("/address/{addressIdx}/thirdAddressName")
    Call<ReportResponse> postMyDong(@Path("addressIdx") Long addressIdx,@Body RegisterInfo registerInfo);

    @POST("/address/{addressIdx}/thirdAddressName")
    Call<ReportResponse> postMyDong2(@Path("addressIdx") Long addressIdx,@Body RegisterInfo registerInfo);



}

