package com.happiness.todaysout.src.main;

import android.util.Log;

import com.happiness.todaysout.src.main.interfaces.MainRetrofitInterface;
import com.happiness.todaysout.src.main.models.AddressGetResponse;
import com.happiness.todaysout.src.main.interfaces.MainActivityView;
import com.happiness.todaysout.src.main.models.DisasterResponse;
import com.happiness.todaysout.src.main.models.HomeWeatherResponse;
import com.happiness.todaysout.src.main.models.MainPostInfo;
import com.happiness.todaysout.src.main.models.MainPostResponse;
import com.happiness.todaysout.src.main.models.MainResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.happiness.todaysout.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getHomeBoard() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getHomeBoard().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse defaultResponse = response.body();
                Log.d("확인","게시판 조회성공2");
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateHomeSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.d("확인","게시판 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMainActivityView.validateFailure(null);
            }
        });
    }

    void getDBoard() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getDBoard().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateDHomeSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {

                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMainActivityView.validateFailure(null);
            }
        });
    }

    void getHomeWeather() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getHomeWeather().enqueue(new Callback<HomeWeatherResponse>() {
            @Override
            public void onResponse(Call<HomeWeatherResponse> call, Response<HomeWeatherResponse> response) {
                Log.d("확인","날씨 조회성공2");
                final HomeWeatherResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    Log.d("확인","날씨 조회성공 null");
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateWeatherSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<HomeWeatherResponse> call, Throwable t) {
                Log.d("확인","날씨 조회 실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMainActivityView.validateFailure(null);
            }
        });
    }

    void getAddress() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getAddress().enqueue(new Callback<AddressGetResponse>() {
            @Override
            public void onResponse(Call<AddressGetResponse> call, Response<AddressGetResponse> response) {
                final AddressGetResponse defaultResponse = response.body();
                Log.d("확인","회원동네 조회성공2");
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateAddressSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<AddressGetResponse> call, Throwable t) {
                Log.d("확인","회원동네 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMainActivityView.validateFailure(null);
            }
        });
    }

    void getD() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getD().enqueue(new Callback<DisasterResponse>() {
            @Override
            public void onResponse(Call<DisasterResponse> call, Response<DisasterResponse> response) {
                final DisasterResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateDSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DisasterResponse> call, Throwable t) {
                Log.d("확인","게시판 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMainActivityView.validateFailure(null);
            }
        });
    }

    void postAddress(MainPostInfo mainPostInfo) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postAddress(mainPostInfo).enqueue(new Callback<MainPostResponse>() {
            @Override
            public void onResponse(Call<MainPostResponse> call, Response<MainPostResponse> response) {
                final MainPostResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validatePostSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<MainPostResponse> call, Throwable t) {

                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMainActivityView.validateFailure(null);
            }
        });
    }


}
