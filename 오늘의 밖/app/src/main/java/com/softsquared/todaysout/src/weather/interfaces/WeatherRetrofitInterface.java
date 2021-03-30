package com.softsquared.todaysout.src.weather.interfaces;

import com.softsquared.todaysout.src.login.models.LoginData;
import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;
import com.softsquared.todaysout.src.weather.models.BoardPostResponse;
import com.softsquared.todaysout.src.weather.models.BoardResponse;
import com.softsquared.todaysout.src.weather.models.CommentResponse;
import com.softsquared.todaysout.src.weather.models.ContentInfo;
import com.softsquared.todaysout.src.weather.models.ContentPatchInfo;
import com.softsquared.todaysout.src.weather.models.DetailResponse;
import com.softsquared.todaysout.src.weather.models.DongResponse;
import com.softsquared.todaysout.src.weather.models.DustResponse;
import com.softsquared.todaysout.src.weather.models.HeartPostResponse;
import com.softsquared.todaysout.src.weather.models.NowResponse;
import com.softsquared.todaysout.src.weather.models.PatchResponse;
import com.softsquared.todaysout.src.weather.models.PostCommentInfo;
import com.softsquared.todaysout.src.weather.models.RegisterInfo;
import com.softsquared.todaysout.src.weather.models.ReportResponse;
import com.softsquared.todaysout.src.weather.models.TodayResponse;
import com.softsquared.todaysout.src.weather.models.UpDownResponse;
import com.softsquared.todaysout.src.weather.models.WeekResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherRetrofitInterface {

    @GET("/address/{addressIdx}/now-weather")
    Call<NowResponse> getNow(@Path("addressIdx") Long addressIdx);


    @POST("/messageBoards/{messageBoardIdx}/comment")
    Call<ReportResponse> postComment(@Path("messageBoardIdx") Long messageBoardIdx,@Body PostCommentInfo postCommentInfo);

    @GET("/address/{addressIdx}/today-hign-low")
    Call<UpDownResponse> getUpDown(@Path("addressIdx") Long addressIdx);


    @GET("/address/{addressIdx}/dust")
    Call<DustResponse> getDust(@Path("addressIdx") Long addressIdx);

    @GET("address/{addressIdx}/time-weathers")
    Call<TodayResponse> getToday(@Path("addressIdx") Long addressIdx);


    @GET("/address/{addressIdx}/messageBoardList")
    Call<BoardResponse> getBoard(@Path("addressIdx") Long addressIdx, @Query("sortType") String type, @Query("page") int page ,@Query("boardType") String boardType);

    @GET("address/{addressIdx}/weekly-weather")
    Call<WeekResponse> getWeek(@Path("addressIdx") Long addressIdx);

    @GET("/messageBoards/{messageBoardIdx}")
    Call<DetailResponse> getDetail(@Path("messageBoardIdx") Long messageBoardIdx);


    @GET("/messageBoards/{messageBoardIdx}/commentList")
    Call<CommentResponse> getComment(@Path("messageBoardIdx") Long messageBoardIdx, @Query("page") int page);


    @POST("/messageBoards/{messageBoardIdx}/heart")
    Call<HeartPostResponse> postHeart(@Path("messageBoardIdx") Long messageBoardIdx);


    @POST("/address/{addressIdx}/messageBoards")
    Call<BoardPostResponse> postContent(@Path("addressIdx") Long addressIdx,@Body ContentInfo contentInfo);

    @GET("/messageBoards/{messageBoardIdx}/heart")
    Call<HeartPostResponse> getHeart(@Path("messageBoardIdx") Long messageBoardIdx);

    @POST("/messageBoards/{messageBoardIdx}/notification")
    Call<ReportResponse> postReport(@Path("messageBoardIdx") Long messageBoardIdx);


    @PATCH("/messageBoards/{messageBoardIdx}")
    Call<PatchResponse> patchContent(@Path("messageBoardIdx") Long messageBoardIdx, @Body ContentPatchInfo contentpatchInfo);

    @GET("/address/{addressIdx}/thirdAddressNameList")
    Call<DongResponse> getDong(@Path("addressIdx") Long addressIdx);


    @POST("/address/{addressIdx}/thirdAddressName")
    Call<ReportResponse> postRegister(@Path("addressIdx") Long addressIdx,@Body RegisterInfo registerInfo);


}

