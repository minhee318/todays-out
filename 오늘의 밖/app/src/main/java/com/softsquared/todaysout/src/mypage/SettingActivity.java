package com.softsquared.todaysout.src.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.BaseActivity;
import com.softsquared.todaysout.src.mypage.Interface.MyView;
import com.softsquared.todaysout.src.mypage.model.MyDongResponse;
import com.softsquared.todaysout.src.mypage.model.MyResponse;
import com.softsquared.todaysout.src.mypage.model.PatchProfileResponse;
import com.softsquared.todaysout.src.weather.models.ReportResponse;

import static com.softsquared.todaysout.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.todaysout.src.ApplicationClass.USER_IDX;
import static com.softsquared.todaysout.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.todaysout.src.ApplicationClass.sSharedPreferences;

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
            Log.d("확인","로그아웃에 성공했습니다.");
    }

    @Override
    public void validateFailure(String message) {

    }
}