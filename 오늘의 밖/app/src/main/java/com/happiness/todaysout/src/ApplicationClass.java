package com.happiness.todaysout.src;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.kakao.auth.KakaoSDK;
import com.happiness.todaysout.config.XAccessTokenInterceptor;
import com.happiness.todaysout.src.login.KaKaoSDKAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    // 테스트 서버 주소
    public static String BASE_URL = "http://15.164.97.234";
    // 실서버 주소
//    public static String BASE_URL = "https://template.softsquared.com/";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences 키 값
    public static String TAG = "확인";

    // JWT Token 값
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";
//    public static String X_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjM4LCJpYXQiOjE2MTY5MzczMzF9.yaOhb1oE50RS2ylIJB918IUlPQ6PEaKCRo73rVTtSGc";

    public static String USER_IDX = "USER_IDX";

    public static String FIRST_DONG = "FIRST_DONG";

    public static String SECOND_DONG = "SECOND_DONG";

    public static String FIRST_ADDRESSIDX = "FIRST_ADDRESSIDX";

    public static String SECOND_ADDRESSIDX = "SECOND_ADDRESSIDX";

    public static boolean IS_FIRST;

    public static boolean IS_SECOND;



    public static String USER_EMAIL = "USER_EMAIL";


    public static String PROFILE_IMAGE = "PROFILE_IMAGE";

    public static String NICKNAME = "NICKNAME";

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Retrofit 인스턴스
    public static Retrofit retrofit;


    private static ApplicationClass instance;



    public static ApplicationClass getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new ApplicationClass();
            return instance;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }

        instance = this;
        KakaoSDK.init(new KaKaoSDKAdapter());
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(8000, TimeUnit.MILLISECONDS)
                    .connectTimeout(8000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}