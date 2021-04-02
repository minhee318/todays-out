package com.happiness.todaysout.src.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.mypage.Interface.MyView;
import com.happiness.todaysout.src.mypage.model.MyDongResponse;
import com.happiness.todaysout.src.mypage.model.MyResponse;
import com.happiness.todaysout.src.mypage.model.PatchProfileResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;


public class MyPageActivity extends BaseActivity implements MyView, View.OnClickListener {

    Long userIdx;
    CircleImageView imgProfile;
    TextView textNickName;
    TextView textTalk;
    TextView textMyHeartNum;
    TextView textFirstGu;
    TextView textDong;
    TextView textSecondGu;
    TextView textSecondDong;
    ImageView imgDot;
    ImageView img_edit;
    LinearLayout LL_editDong;
    LinearLayout LL_myheart;
    LinearLayout LL_mytalk;
    ImageView btn_backDetail;
    String firstGu;
    String secondGu;
    ArrayList<Long> List = new ArrayList<>();


    @Override
    protected void onResume() {

        if (userIdx == sSharedPreferences.getLong("USER_IDX", -1)) {
            tryGetMyInfo(userIdx);
            imgDot.setVisibility(View.VISIBLE);
            img_edit.setVisibility(View.VISIBLE);
            LL_editDong.setVisibility(View.VISIBLE);
        } else {
            tryGetMyInfo(userIdx);
            imgDot.setVisibility(View.INVISIBLE);
            img_edit.setVisibility(View.INVISIBLE);
            LL_editDong.setVisibility(View.INVISIBLE);
        }


        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        btn_backDetail = findViewById(R.id.btn_backDetail);

        btn_backDetail.setOnClickListener(this);

        imgProfile = findViewById(R.id.imgProfile);
        textNickName = findViewById(R.id.textNickName);
        textTalk = findViewById(R.id.textTalk);
        textMyHeartNum = findViewById(R.id.textMyHeartNum);
        textFirstGu = findViewById(R.id.textFirstGu);
        textDong = findViewById(R.id.textDong);
        textSecondGu = findViewById(R.id.textSecondGu);
        textSecondDong = findViewById(R.id.textSecondDong);


        imgDot = findViewById(R.id.imgDot);
        img_edit = findViewById(R.id.img_edit);
        LL_editDong = findViewById(R.id.LL_editDong);

        LL_mytalk = findViewById(R.id.LL_mytalk);
        LL_myheart = findViewById(R.id.LL_myheart);

        LL_mytalk.setOnClickListener(this);
        LL_myheart.setOnClickListener(this);

        imgDot.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        LL_editDong.setOnClickListener(this);


        Intent getIntent = getIntent();
        userIdx = getIntent.getLongExtra("userIdx", -1);


        if (userIdx == sSharedPreferences.getLong("USER_IDX", -1)) {
            tryGetMyInfo(userIdx);
            imgDot.setVisibility(View.VISIBLE);
            img_edit.setVisibility(View.VISIBLE);
            LL_editDong.setVisibility(View.VISIBLE);
        } else {
            tryGetMyInfo(userIdx);
            imgDot.setVisibility(View.INVISIBLE);
            img_edit.setVisibility(View.INVISIBLE);
            LL_editDong.setVisibility(View.INVISIBLE);
        }

    }


    private void tryGetMyInfo(Long userIdx) {
        showProgressDialog();

        final MyService myService = new MyService(this);
        myService.getMy(userIdx);
    }

    @Override
    public void validateMySuccess(MyResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1011:
                    Log.d("확인", "회원정보 조회 성공");
                    if (response.getResult().getProfile() != null) {
                        Glide
                                .with(this)
                                .load(response.getResult().getProfile())
                                .into(imgProfile);
                    }


                    if (response.getResult().getNickname() != null) {
                        textNickName.setText(response.getResult().getNickname());
                    }

                    if (response.getResult().getTalkNum() != null) {
                        textTalk.setText(response.getResult().getTalkNum());
                    } else {
                        textTalk.setText("0");
                    }

                    if (response.getResult().getHeartNum() != null) {
                        textMyHeartNum.setText(response.getResult().getHeartNum());
                    } else {
                        textMyHeartNum.setText("0");
                    }

                    if (response.getResult().getAddress().get(0).getGu() != null) {
                        textFirstGu.setText(response.getResult().getAddress().get(0).getGu());
                    }

                    if (response.getResult().getAddress().get(0).getDong() != null) {
                        textDong.setText(response.getResult().getAddress().get(0).getDong());
                    }

                    if (response.getResult().getAddress().get(1).getGu() != null) {
                        textSecondGu.setText(response.getResult().getAddress().get(1).getGu());
                    }

                    if (response.getResult().getAddress().get(1).getDong() != null) {
                        textSecondDong.setText(response.getResult().getAddress().get(1).getDong());
                    }

                    firstGu = response.getResult().getAddress().get(0).getGu();
                    secondGu = response.getResult().getAddress().get(1).getGu();

                    List.add(response.getResult().getAddress().get(0).getAddressIdx());
                    List.add(response.getResult().getAddress().get(1).getAddressIdx());

                    break;
            }
        } else {
            switch (response.getCode()) {
                case 3010:
                    showCustomToast("탈퇴한 회원입니다.");
                    Log.d("확인", "탈퇴한 회원입니다.");
                    finish();


                    break;
                default:
                    Log.d("확인", response.getMessage());
                    showCustomToast("네트워크 연결이 원활하지 않습니다.");
                    break;

            }
            }
        }

        @Override
        public void validatePatchProfileSuccess (PatchProfileResponse response){

        }

        @Override
        public void validateMyDongSuccess (MyDongResponse response){

        }

        @Override
        public void validateMyDong2Success (MyDongResponse response){

        }

        @Override
        public void validateMyPostDongSuccess (ReportResponse response){

        }

        @Override
        public void validateMyPostDong2Success (ReportResponse response){

        }


        @Override
        public void validateByeSuccess (ReportResponse response){

        }

        @Override
        public void validateFailure (String message){

        }

        @Override
        public void onClick (View view){
            switch (view.getId()) {

                case R.id.imgDot:
                    Intent setting = new Intent(this, SettingActivity.class);
                    startActivity(setting);

                    break;
                case R.id.img_edit:
                    Intent profileedit = new Intent(MyPageActivity.this, ProfileEditActivity.class);
                    startActivity(profileedit);
                    break;
                case R.id.LL_editDong:
                    Intent dong = new Intent(MyPageActivity.this, DongSettingActivity.class);
                    dong.putExtra("List", List);
                    dong.putExtra("firstgu", firstGu);
                    dong.putExtra("secondgu", secondGu);
                    startActivity(dong);
                    break;
                case R.id.LL_mytalk:

                    break;

                case R.id.btn_backDetail:
                    finish();
                    break;


            }
        }
    }