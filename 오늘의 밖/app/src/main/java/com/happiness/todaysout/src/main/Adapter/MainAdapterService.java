package com.happiness.todaysout.src.main.Adapter;


import android.util.Log;

import com.happiness.todaysout.src.main.Adapter.Interface.MainAdapterRetrofitInterface;
import com.happiness.todaysout.src.main.Adapter.Interface.MainAdapterView;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.happiness.todaysout.src.ApplicationClass.getRetrofit;

class MainAdapterService {
    private final MainAdapterView mMainAdapterView;

    MainAdapterService(final MainAdapterView mMainAdapterView) {
        this.mMainAdapterView = mMainAdapterView;
    }

    void deleteGu(Long addressIdx) {

        final MainAdapterRetrofitInterface mainAdapterRetrofitInterface = getRetrofit().create(MainAdapterRetrofitInterface.class);

        mainAdapterRetrofitInterface.deleteGu(addressIdx).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {

                final ReportResponse adapterResponse = response.body();

                Log.d("확인","삭제성공2");
                if (adapterResponse == null) {
                    Log.d("확인","삭제성공null");
                    mMainAdapterView.validateFailure(null);
                    return;
                }

                mMainAdapterView.validateDeleteGuSuccess(adapterResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d("확인","삭제실패");
                mMainAdapterView.validateFailure(null);
            }
        });
    }

    void patchAddress() {

        final MainAdapterRetrofitInterface mainAdapterRetrofitInterface = getRetrofit().create(MainAdapterRetrofitInterface.class);

        mainAdapterRetrofitInterface.patchAddress().enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {

                final ReportResponse adapterResponse = response.body();
                if (adapterResponse == null) {
                    mMainAdapterView.validateFailure(null);
                    return;
                }

                mMainAdapterView.validatePatchGuSuccess(adapterResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {

                mMainAdapterView.validateFailure(null);
            }
        });
    }
}
