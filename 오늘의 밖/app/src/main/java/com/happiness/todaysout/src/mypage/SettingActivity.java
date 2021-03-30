package com.happiness.todaysout.src.mypage;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.login.LoginActivity;
import com.happiness.todaysout.src.mypage.Interface.MyView;
import com.happiness.todaysout.src.mypage.model.MyDongResponse;
import com.happiness.todaysout.src.mypage.model.MyResponse;
import com.happiness.todaysout.src.mypage.model.PatchProfileResponse;
import com.happiness.todaysout.src.weather.WeatherActivity;
import com.happiness.todaysout.src.weather.WriteActivity;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_DONG;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_DONG;
import static com.happiness.todaysout.src.ApplicationClass.USER_EMAIL;
import static com.happiness.todaysout.src.ApplicationClass.USER_IDX;
import static com.happiness.todaysout.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class SettingActivity extends BaseActivity implements MyView {

    TextView textEmail;
    ImageView btn_edit;

    TextView textLogout;
    TextView textBye;

    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textBye =findViewById(R.id.textBye);
        textBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor2 = sSharedPreferences.edit();
                editor2.remove(X_ACCESS_TOKEN);
                editor2.remove(USER_IDX);
                editor2.remove(FIRST_DONG);
                editor2.remove(SECOND_DONG);
                editor2.apply();
                trydeleteInfo();
            }
        });


        textLogout = findViewById(R.id.textLogout);
        textLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor2 = sSharedPreferences.edit();
                editor2.remove(X_ACCESS_TOKEN);
                editor2.remove(USER_IDX);
                editor2.remove(FIRST_DONG);
                editor2.remove(SECOND_DONG);
                editor2.apply();
            }
        });


        textEmail = findViewById(R.id.textEmail);
        btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(SettingActivity.this, ProfileEditActivity.class);
                startActivity(edit);
            }
        });


       if(sSharedPreferences.getString(USER_EMAIL, "gg")!=null){
           textEmail.setText(sSharedPreferences.getString(USER_EMAIL, "gg"));

       }else{
           textEmail.setText("프로필 설정 화면에서 이메일을 등록하세요");
       }

    }

    private void trydeleteInfo() {
        showProgressDialog();
        final MyService myService = new MyService(this);
        myService.deleteBye();
    }

    @Override
    public void validateMySuccess(MyResponse response) {

    }

    @Override
    public void validatePatchProfileSuccess(PatchProfileResponse response) {

    }

    @Override
    public void validateMyDongSuccess(MyDongResponse response) {

    }

    @Override
    public void validateMyDong2Success(MyDongResponse response) {

    }

    @Override
    public void validateMyPostDongSuccess(ReportResponse response) {

    }

    @Override
    public void validateMyPostDong2Success(ReportResponse response) {

    }

    @Override
    public void validateByeSuccess(ReportResponse response) {
        hideProgressDialog();
            Log.d("확인","회원탈퇴에 성공했습니다.");
        Intent write = new Intent(this, LoginActivity.class);
        startActivity(write);

    }

    @Override
    public void validateFailure(String message) {

    }
}