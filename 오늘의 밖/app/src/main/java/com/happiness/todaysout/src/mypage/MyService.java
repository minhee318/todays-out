package com.happiness.todaysout.src.mypage;

import android.util.Log;

import com.happiness.todaysout.src.mypage.Interface.MyRetrofitInterface;
import com.happiness.todaysout.src.mypage.Interface.MyView;
import com.happiness.todaysout.src.mypage.model.MyDongResponse;
import com.happiness.todaysout.src.mypage.model.MyResponse;
import com.happiness.todaysout.src.mypage.model.PatchInfo;
import com.happiness.todaysout.src.mypage.model.PatchProfileResponse;
import com.happiness.todaysout.src.weather.models.RegisterInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.happiness.todaysout.src.ApplicationClass.getRetrofit;

class MyService {
    private final MyView mMyView;

    MyService(final MyView mMyView) {
        this.mMyView = mMyView;
    }

    void getMy(Long userIdx) {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.getMy(userIdx).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                final MyResponse defaultResponse = response.body();
                Log.d("확인","회원정보 조회성공2");
                if (defaultResponse == null) {
                    mMyView.validateFailure(null);
                    return;
                }

                mMyView.validateMySuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("확인","회원정보 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMyView.validateFailure(null);
            }
        });
    }

    void deleteBye() {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.deleteBye().enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();
                Log.d("확인","회원탈퇴 조회성공2");
                if (defaultResponse == null) {
                    Log.d("확인","회원탈퇴 null");
                    mMyView.validateFailure(null);
                    return;
                }

                mMyView.validateByeSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d("확인","회원탈퇴 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMyView.validateFailure(null);
            }
        });
    }

    void patchProfile(PatchInfo patchInfo) {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.patchProfile(patchInfo).enqueue(new Callback<PatchProfileResponse>() {
            @Override
            public void onResponse(Call<PatchProfileResponse> call, Response<PatchProfileResponse> response) {
                final PatchProfileResponse defaultResponse = response.body();
                Log.d("확인","회원정보 수정 조회성공2");
                if (defaultResponse == null) {
                    mMyView.validateFailure(null);
                    return;
                }

                mMyView.validatePatchProfileSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<PatchProfileResponse> call, Throwable t) {
                Log.d("확인","회원정보 수정 조회실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMyView.validateFailure(null);
            }
        });
    }


    void getMyDong(Long addressIdx) {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.getMyDong(addressIdx).enqueue(new Callback<MyDongResponse>() {
            @Override
            public void onResponse(Call<MyDongResponse> call, Response<MyDongResponse> response) {
                final MyDongResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMyView.validateFailure(null);
                    return;
                }

                mMyView.validateMyDongSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<MyDongResponse> call, Throwable t) {

                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMyView.validateFailure(null);
            }
        });
    }

    void getMyDong2(Long addressIdx) {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.getMyDong(addressIdx).enqueue(new Callback<MyDongResponse>() {
            @Override
            public void onResponse(Call<MyDongResponse> call, Response<MyDongResponse> response) {
                final MyDongResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMyView.validateFailure(null);
                    return;
                }

                mMyView.validateMyDong2Success(defaultResponse);
            }

            @Override
            public void onFailure(Call<MyDongResponse> call, Throwable t) {

                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMyView.validateFailure(null);
            }
        });
    }

    void postMyDong(Long addressIdx, RegisterInfo registerInfo) {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.postMyDong(addressIdx,registerInfo).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMyView.validateFailure(null);
                    return;
                }

                mMyView.validateMyPostDongSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {

                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMyView.validateFailure(null);
            }
        });
    }

    void postMyDong2(Long addressIdx, RegisterInfo registerInfo) {
        final MyRetrofitInterface myRetrofitInterface = getRetrofit().create(MyRetrofitInterface.class);
        myRetrofitInterface.postMyDong2(addressIdx,registerInfo).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                final ReportResponse defaultResponse = response.body();

                if (defaultResponse == null) {
                    mMyView.validateFailure(null);
                    return;
                }

                mMyView.validateMyPostDong2Success(defaultResponse);
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {

                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mMyView.validateFailure(null);
            }
        });
    }




}
