package com.softsquared.todaysout.src.login;

import android.util.Log;

import com.softsquared.todaysout.src.login.Interface.LoginActivityView;
import com.softsquared.todaysout.src.login.Interface.LoginRetrofitInterface;
import com.softsquared.todaysout.src.login.models.AccessToken;
import com.softsquared.todaysout.src.login.models.LoginData;
import com.softsquared.todaysout.src.login.models.LoginResponse;
import com.softsquared.todaysout.src.login.models.SignInResponse;
import com.softsquared.todaysout.src.login.models.SplashResponse;
import com.softsquared.todaysout.src.main.interfaces.MainActivityView;
import com.softsquared.todaysout.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.todaysout.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.todaysout.src.ApplicationClass.getRetrofit;

class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postKakaoToken(AccessToken token) {

        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postKakaoLogin(token).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body(); //서버에서 받아온 값
                Log.d("확인","카카오 로그인 성공2");
                if (loginResponse == null) {
                    mLoginActivityView.validateFailure(null);

                    Log.d("확인","카카오 로그인 성공null");
                    return;
                }

                mLoginActivityView.validateKakaoSuccess(loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("확인","카카오 로그인 실패");
                mLoginActivityView.validateFailure(null);
            }
        });
    }

    void getAutoLogin() {
        final LoginRetrofitInterface splashRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        splashRetrofitInterface.getAutoLogin().enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(Call<SplashResponse> call, Response<SplashResponse> response) {
                final SplashResponse defaultResponse = response.body();
                Log.d("확인","자동로그인2");
                if (defaultResponse == null) {
                    Log.d("확인","자동로그인2null");
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateAutoLoginSuccess(defaultResponse.getCode());
            }

            @Override
            public void onFailure(Call<SplashResponse> call, Throwable t) {
                Log.d("확인","자동로그인실패");
                t.printStackTrace(); //에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
                mLoginActivityView.validateFailure(null);
            }
        });
    }

    void postSignIn(LoginData params) {

        final LoginRetrofitInterface signinRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);

        signinRetrofitInterface.postSignIn(params).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                final SignInResponse signinResponse = response.body();
                Log.d("확인","회원가입성공2");
                if (signinResponse == null) {
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateSignInSuccess(signinResponse);
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d("확인","회원가입실패");
                mLoginActivityView.validateFailure(null);
            }
        });
    }
}
