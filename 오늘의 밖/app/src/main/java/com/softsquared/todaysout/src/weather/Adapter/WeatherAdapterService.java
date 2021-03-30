package com.softsquared.todaysout.src.weather.Adapter;



import com.softsquared.todaysout.src.weather.Adapter.Interface.WeatherAdapterRetrofitInterface;
import com.softsquared.todaysout.src.weather.Adapter.Interface.WeatherAdapterView;
import com.softsquared.todaysout.src.weather.models.ReportResponse;
import static com.softsquared.todaysout.src.ApplicationClass.getRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class WeatherAdapterService {
    private final WeatherAdapterView mWeatherAdapterView;

    WeatherAdapterService(final WeatherAdapterView mWeatherAdapterView) {
        this.mWeatherAdapterView = mWeatherAdapterView;
    }

    void deleteContent(Long messageBoardIdx) {

        final WeatherAdapterRetrofitInterface weatherAdapterRetrofitInterface = getRetrofit().create(WeatherAdapterRetrofitInterface.class);

        weatherAdapterRetrofitInterface.deleteContent(messageBoardIdx).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {

                final ReportResponse adapterResponse = response.body();
                if (adapterResponse == null) {
                    mWeatherAdapterView.validateFailure(null);
                    return;
                }

                mWeatherAdapterView.validateDeleteSuccess(adapterResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {

                mWeatherAdapterView.validateFailure(null);
            }
        });
    }

    void deleteComment(Long messageBoardIdx,Long commentIdx,Long userIdx) {

        final WeatherAdapterRetrofitInterface weatherAdapterRetrofitInterface = getRetrofit().create(WeatherAdapterRetrofitInterface.class);

        weatherAdapterRetrofitInterface.deleteComment(messageBoardIdx,commentIdx,userIdx).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {

                final ReportResponse adapterResponse = response.body();
                if (adapterResponse == null) {
                    mWeatherAdapterView.validateFailure(null);
                    return;
                }

                mWeatherAdapterView.validateDeleteCommentSuccess(adapterResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {

                mWeatherAdapterView.validateFailure(null);
            }
        });
    }

    void postReportComment(Long commentIdx) {

        final WeatherAdapterRetrofitInterface weatherAdapterRetrofitInterface = getRetrofit().create(WeatherAdapterRetrofitInterface.class);

        weatherAdapterRetrofitInterface.postReportComment(commentIdx).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {

                final ReportResponse adapterResponse = response.body();
                if (adapterResponse == null) {
                    mWeatherAdapterView.validateFailure(null);
                    return;
                }

                mWeatherAdapterView.validatePostReportCommentSuccess(adapterResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {

                mWeatherAdapterView.validateFailure(null);
            }
        });
    }
}
