package com.happiness.todaysout.config;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.happiness.todaysout.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
       final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
//        final String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjM4LCJpYXQiOjE2MTY5MzczMzF9.yaOhb1oE50RS2ylIJB918IUlPQ6PEaKCRo73rVTtSGc";
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken);
//            builder.addHeader("X-ACCESS-TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjM4LCJpYXQiOjE2MTY5MzczMzF9.yaOhb1oE50RS2ylIJB918IUlPQ6PEaKCRo73rVTtSGc");
//            Log.d("확인",jwtToken);
        }
        return chain.proceed(builder.build());
    }
}
