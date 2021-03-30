package com.happiness.todaysout.src.emergency;

import android.util.Log;

import com.happiness.todaysout.src.emergency.Interface.JActivityView;
import com.happiness.todaysout.src.emergency.Interface.JRetrofitInterface;
import com.happiness.todaysout.src.emergency.models.JResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.happiness.todaysout.src.ApplicationClass.getRetrofit;

class JService {
    private final JActivityView mJActivityView;

    JService(final JActivityView mJActivityView) {
        this.mJActivityView = mJActivityView;
    }

    void getJ(Long userIdx,int month,int day, String city, String state) {
        final JRetrofitInterface jRetrofitInterface = getRetrofit().create(JRetrofitInterface.class);
        jRetrofitInterface.getJ(userIdx,month,day,city,state).enqueue(new Callback<JResponse>() {
            @Override
            public void onResponse(Call<JResponse> call, Response<JResponse> response) {
                final JResponse defaultResponse = response.body();
                Log.d("확인","재난조회 성공2");
                if (defaultResponse == null) {
                    mJActivityView.validateFailure(null);
                    return;
                }

                mJActivityView.validateJSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<JResponse> call, Throwable t) {
                Log.d("확인","재난조회 성공2");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mJActivityView.validateFailure(null);
            }
        });
    }








}
