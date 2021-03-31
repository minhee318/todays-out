package com.happiness.todaysout.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.login.Interface.LoginActivityView;
import com.happiness.todaysout.src.login.models.LoginResponse;
import com.happiness.todaysout.src.login.models.SignInResponse;
import com.happiness.todaysout.src.main.MainActivity;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.FIRST_DONG;
import static com.happiness.todaysout.src.ApplicationClass.NICKNAME;
import static com.happiness.todaysout.src.ApplicationClass.PROFILE_IMAGE;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_DONG;
import static com.happiness.todaysout.src.ApplicationClass.USER_EMAIL;
import static com.happiness.todaysout.src.ApplicationClass.USER_IDX;
import static com.happiness.todaysout.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class SplashActivity extends BaseActivity implements LoginActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

//
//        SharedPreferences.Editor editor2 = sSharedPreferences.edit();
//        editor2.remove(X_ACCESS_TOKEN);
//        editor2.remove(USER_IDX);
//        editor2.remove(FIRST_DONG);
//        editor2.remove(SECOND_DONG);
//        editor2.remove(FIRST_ADDRESSIDX);
//        editor2.remove(SECOND_ADDRESSIDX);
//
//        editor2.apply();


        Log.d("확인","스플래쉬화면:"+sSharedPreferences.getString(X_ACCESS_TOKEN, null));

        if (sSharedPreferences.getString(X_ACCESS_TOKEN, null) != null) {
            confirmJWT();

        } else {
            Handler hd = new Handler();
            hd.postDelayed(new splashHandler2(), 1000);
        }
    }


    private void confirmJWT() {
        Log.d("확인","자동로그인1");
        final LoginService splashService = new LoginService(this);
        splashService.getAutoLogin();
    }

    @Override
    public void validateKakaoSuccess(LoginResponse response) {

    }

    @Override
    public void validateAutoLoginSuccess(int code) {
        Log.d("확인","자동로그인 성공"+code);
        if (code == 1014) { //이미 회원이다.
            Handler hd = new Handler();
            hd.postDelayed(new splashHandler(), 1000);
        } else if (code == 3010) { //회원이 아니다.
            sSharedPreferences.edit().remove(USER_IDX).apply();
            sSharedPreferences.edit().remove(X_ACCESS_TOKEN).apply();
            sSharedPreferences.edit().remove(USER_EMAIL).apply();
            sSharedPreferences.edit().remove(USER_EMAIL).apply();
            sSharedPreferences.edit().remove(PROFILE_IMAGE).apply();
            sSharedPreferences.edit().remove(NICKNAME).apply();
            sSharedPreferences.edit().remove(FIRST_ADDRESSIDX).apply();
            sSharedPreferences.edit().remove(SECOND_ADDRESSIDX).apply();
            Handler hd = new Handler();
            hd.postDelayed(new splashHandler2(), 1000);
        } else {

            Toast.makeText(getApplicationContext(), "네트워크 연결이 원활하지 않습니다.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void validateSignInSuccess(SignInResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }

    private class splashHandler implements Runnable { //회원가입이 된 상태이므로 메인으로
        public void run() {

                Intent main = new Intent(getApplication(), MainActivity.class);
                startActivity(main);
                SplashActivity.this.finish();

        }
    }

    private class splashHandler2 implements Runnable { //회원이 아닌 상태 이므로 회원가입
        public void run() {

                startActivity(new Intent(getApplication(),LoginActivity.class));
                SplashActivity.this.finish();
            }

        }
    }
