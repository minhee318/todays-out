package com.softsquared.todaysout.src.alarm;

import android.util.Log;

import com.softsquared.todaysout.src.alarm.Interface.AlarmActivityView;
import com.softsquared.todaysout.src.alarm.Interface.AlarmRetrofitInterface;
import com.softsquared.todaysout.src.alarm.model.AlarmInfo;
import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.mypage.Interface.MyRetrofitInterface;
import com.softsquared.todaysout.src.mypage.Interface.MyView;
import com.softsquared.todaysout.src.mypage.model.MyResponse;
import com.softsquared.todaysout.src.mypage.model.PatchInfo;
import com.softsquared.todaysout.src.mypage.model.PatchProfileResponse;
import com.softsquared.todaysout.src.weather.models.ReportResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.todaysout.src.ApplicationClass.getRetrofit;

class AlarmService {
    private final AlarmActivityView mAlarmActivityView;

    AlarmService(final AlarmActivityView mAlarmActivityView) {
        this.mAlarmActivityView = mAlarmActivityView;
    }

    void postAlarm(AlarmInfo alarmInfo) {
        final AlarmRetrofitInterface alarmRetrofitInterface = getRetrofit().create(AlarmRetrofitInterface.class);
        alarmRetrofitInterface.postAlarm(alarmInfo).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();
                Log.d("확인","알림등록 성공2");
                if (defaultResponse == null) {
                    mAlarmActivityView.validateFailure(null);
                    return;
                }

                mAlarmActivityView.validateAlarmSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d("확인","알림등록 실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mAlarmActivityView.validateFailure(null);
            }
        });
    }

    void getAddress() {
        final AlarmRetrofitInterface alarmRetrofitInterface = getRetrofit().create(AlarmRetrofitInterface.class);
        alarmRetrofitInterface.getAddress().enqueue(new Callback<AddressGetResponse>() {
            @Override
            public void onResponse(Call<AddressGetResponse> call, Response<AddressGetResponse> response) {
                final AddressGetResponse defaultResponse = response.body();
                Log.d("확인","회원동네조회 성공2");
                if (defaultResponse == null) {
                    mAlarmActivityView.validateFailure(null);
                    return;
                }

                mAlarmActivityView.validateDongSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<AddressGetResponse> call, Throwable t) {
                Log.d("확인","회원동네조회 실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mAlarmActivityView.validateFailure(null);
            }
        });
    }


    void postSwitch(boolean notice,boolean disaster) {
        final AlarmRetrofitInterface alarmRetrofitInterface = getRetrofit().create(AlarmRetrofitInterface.class);
        alarmRetrofitInterface.postSwitch(notice,disaster).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();
                Log.d("확인","스위치 성공2");
                if (defaultResponse == null) {
                    mAlarmActivityView.validateFailure(null);
                    return;
                }

                mAlarmActivityView.validateSwitchSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d("확인","스위치 실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mAlarmActivityView.validateFailure(null);
            }
        });
    }





}
