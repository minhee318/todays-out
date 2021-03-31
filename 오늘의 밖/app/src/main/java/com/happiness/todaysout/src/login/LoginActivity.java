package com.happiness.todaysout.src.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.login.Interface.LoginActivityView;
import com.happiness.todaysout.src.login.models.AccessToken;
import com.happiness.todaysout.src.login.models.LoginResponse;
import com.happiness.todaysout.src.login.models.SignInResponse;
import com.happiness.todaysout.src.main.MainActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.happiness.todaysout.src.ApplicationClass.USER_EMAIL;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginActivityView {


    //카카오 로그인
    private SessionCallback callback;
    ImageView btnKakao;
    LoginButton com_kakao_login1;
    String email;

    public String kakao_token;

    SharedPreferences.Editor editor = sSharedPreferences.edit();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnKakao = findViewById(R.id.btnKakao);
        btnKakao.setOnClickListener(this);





        com_kakao_login1 = findViewById(R.id.com_kakao_login1);
        btnKakao.setOnClickListener(v -> {
            kakaoData();
            com_kakao_login1.performClick();
        });




//        Log.d("확인",getKeyHash(this));

    }


    public static String getKeyHash(final Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (packageInfo == null)
                return null;

            for (Signature signature : packageInfo.signatures) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), android.util.Base64.NO_WRAP);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnKakao:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                break;


        }

    }


    private void postKakaoTokenInfo(String token) {
        showProgressDialog();
        Log.d("확인","카카오 로그인 성공1");
        final LoginService loginService = new LoginService(this);
        loginService.postKakaoToken(new AccessToken(token));
    }

    private void kakaoData() {

        callback = new SessionCallback(); //SessionCallback 초기화
        Session.getCurrentSession().addCallback(callback); //현재 세션에 콜백 붙임


        //토큰 만료시 갱신을 시켜준다.
        if (Session.getCurrentSession().isOpenable()) {
            Session.getCurrentSession().checkAndImplicitOpen();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) { //카카오 로그인 액티비티에서 넘어온 경우일 때 실행
            return;
        }

    }

    @Override
    public void validateKakaoSuccess(LoginResponse response) {
        Log.d("확인","카카오 로그인 성공");
        hideProgressDialog();
        if (response.getIsSuccess()) {
            Log.d("확인","카카오 로그인 성공2"+response.getCode());
            switch (response.getCode()) {
                case 1223: //신규


                    Log.d("확인",response.getMessage());

                    Log.d("확인","카카오 로그인 jwt"+response.getResult().getJwt());
                    Log.d("확인","카카오 로그인 userIdx"+response.getResult().getUserIdx());



                    if(response.getResult().getEmail() != null){
                        email = response.getResult().getEmail();
                        editor.putString(USER_EMAIL, email);
                    }else{
                        email = "이메일등록안함";
                        editor.putString(USER_EMAIL, email);
                    }
                    editor.apply();

                    Intent snsId = new Intent(this, ProfileActivity.class);
                    snsId.putExtra("snsId",response.getResult().getSnsId());
                    startActivity(snsId);
                    finish();


                    break;

                case 1224:
//
                    Log.d("확인",response.getMessage());
//
////                    Log.d("확인","카카오 로그인 jwt"+response.getResult().getJwt());
////                    Log.d("확인","카카오 로그인 userIdx"+response.getResult().getUserIdx());
////
////
////                    if(response.getResult().getEmail() != null){
////                        email = response.getResult().getEmail();
////                        editor.putString(USER_EMAIL, email);
////                    }else{
////                        email = "이메일등록안함";
////                        editor.putString(USER_EMAIL, email);
////                    }
////                    editor.apply();
//
//                    Intent snsId2 = new Intent(this, MainActivity.class);
////                    snsId2.putExtra("snsId",response.getResult().getSnsId());
//                    startActivity(snsId2);
//                    finish();


                    break;
            }
        } else {


                    Toast.makeText(getApplicationContext(), getString(R.string.network_error), Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void validateAutoLoginSuccess(int code) {

    }

    @Override
    public void validateSignInSuccess(SignInResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {

            kakao_token = Session.getCurrentSession().getTokenInfo().getAccessToken();

            Log.d("확인","카카오토큰:"+kakao_token);
            postKakaoTokenInfo(kakao_token);
            //로그인 세션이 열렸을 때.


        }


        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            //로그인 세션이 정상적으로 열리지 않았을 때.
            if (exception != null) {
                Log.e(TAG, "exception : " + exception);
            }
        }
    }

}