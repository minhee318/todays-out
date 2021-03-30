package com.happiness.todaysout.src.alarm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.alarm.Interface.AlarmActivityView;
import com.happiness.todaysout.src.alarm.model.AlarmInfo;

import com.happiness.todaysout.src.main.models.AddressGetResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class AlarmInfoActivity extends BaseActivity implements AlarmActivityView {
    ImageView btn_cancel;
    CheckBox natural1;
    CheckBox natural2;
    CheckBox natural3;
    CheckBox natural4;
    CheckBox natural5;
    CheckBox natural6;
    CheckBox natural7;
    CheckBox natural8;
    CheckBox natural9;
    CheckBox natural10;

    CheckBox society1;
    CheckBox society2;
    CheckBox society3;
    CheckBox society4;

    CheckBox society6;
    CheckBox society7;
    CheckBox society8;
    CheckBox society9;
    CheckBox society10;
    CheckBox society11;
    CheckBox society12;
    ImageView btn_allsociety;
    ImageView btn_allnatural;
    boolean allCheck = false;
    boolean allCheck2 = false;




    ImageView btn_ok;

    ArrayList<String> sortList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_info);

        btn_ok = findViewById(R.id.btn_ok);


        btn_allnatural = findViewById(R.id.btn_allnatural);
        btn_allsociety = findViewById(R.id.btn_allsociety);


        btn_allsociety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allCheck2){
                    society1.setChecked(false);
                    society2.setChecked(false);
                    society3.setChecked(false);
                    society4.setChecked(false);
                    society6.setChecked(false);
                    society7.setChecked(false);
                    society8.setChecked(false);
                    society9.setChecked(false);
                    society10.setChecked(false);
                    society11.setChecked(false);
                    society12.setChecked(false);


                    sortList.remove("감염병");
                    sortList.remove("미세먼지");
                    sortList.remove("화재");
                    sortList.remove("수질");
                    sortList.remove("위험물");
                    sortList.remove("붕괴");
                    sortList.remove("교통사고");
                    sortList.remove("현장사고");
                    sortList.remove("자원수급");
                    sortList.remove("통신");
                    sortList.remove("우주");


                    allCheck2 =false;
                }else{
                    society1.setChecked(true);
                    society2.setChecked(true);
                    society3.setChecked(true);
                    society4.setChecked(true);
                    society6.setChecked(true);
                    society7.setChecked(true);
                    society8.setChecked(true);
                    society9.setChecked(true);
                    society10.setChecked(true);
                    society11.setChecked(true);
                    society12.setChecked(true);


                    sortList.add("감염병");
                    sortList.add("미세먼지");
                    sortList.add("화재");
                    sortList.add("수질");
                    sortList.add("위험물");
                    sortList.add("붕괴");
                    sortList.add("교통사고");
                    sortList.add("현장사고");
                    sortList.add("자원수급");
                    sortList.add("통신");
                    sortList.add("우주");



                    allCheck2 =true;
                }
            }
        });


        btn_allnatural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allCheck){
                    natural1.setChecked(false);
                    natural2.setChecked(false);
                    natural3.setChecked(false);
                    natural4.setChecked(false);
                    natural5.setChecked(false);
                    natural6.setChecked(false);
                    natural7.setChecked(false);
                    natural8.setChecked(false);
                    natural9.setChecked(false);
                    natural10.setChecked(false);


                    sortList.remove("태풍");
                    sortList.remove("해일");
                    sortList.remove("홍수");
                    sortList.remove("호우");
                    sortList.remove("강풍");
                    sortList.remove("대설");
                    sortList.remove("한파");
                    sortList.remove("폭염");
                    sortList.remove("건조");
                    sortList.remove("황사");

                    allCheck =false;


                }else{
                    natural1.setChecked(true);
                    natural2.setChecked(true);
                    natural3.setChecked(true);
                    natural4.setChecked(true);
                    natural5.setChecked(true);
                    natural6.setChecked(true);
                    natural7.setChecked(true);
                    natural8.setChecked(true);
                    natural9.setChecked(true);
                    natural10.setChecked(true);


                    sortList.add("태풍");
                    sortList.add("해일");
                    sortList.add("홍수");
                    sortList.add("호우");
                    sortList.add("강풍");
                    sortList.add("대설");
                    sortList.add("한파");
                    sortList.add("폭염");
                    sortList.add("건조");
                    sortList.add("황사");

                    allCheck =true;
                }

            }
        });


        btn_cancel = findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        natural1 = (CheckBox) findViewById(R.id.natural1) ;
        natural1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("태풍");

                } else {
                   sortList.remove("태풍");

                }
            }
        }) ;

        natural2 = (CheckBox) findViewById(R.id.natural2) ;
        natural2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("해일");

                } else {
                    sortList.remove("해일");

                }
            }
        }) ;

        natural3 = (CheckBox) findViewById(R.id.natural3) ;
        natural3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("홍수");
                } else {
                    sortList.remove("홍수");
                }
            }
        }) ;

        natural4 = (CheckBox) findViewById(R.id.natural4) ;
        natural4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("호우");
                } else {
                    sortList.remove("호우");
                }
            }
        }) ;

        natural5 = (CheckBox) findViewById(R.id.natural5) ;
        natural5.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("강풍");
                } else {
                    sortList.remove("강풍");
                }
            }
        }) ;

        natural6 = (CheckBox) findViewById(R.id.natural6) ;
        natural6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("대설");
                } else {
                    sortList.remove("대설");
                }
            }
        }) ;

        natural7 = (CheckBox) findViewById(R.id.natural7) ;
        natural7.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("한파");
                } else {
                    sortList.remove("한파");
                }
            }
        }) ;

        natural8 = (CheckBox) findViewById(R.id.natural8) ;
        natural8.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("폭염");
                } else {
                    sortList.remove("폭염");
                }
            }
        }) ;

        natural9 = (CheckBox) findViewById(R.id.natural9) ;
        natural9.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("건조");
                } else {
                    sortList.remove("건조");
                }
            }
        }) ;

        natural10 = (CheckBox) findViewById(R.id.natural10) ;
        natural10.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("황사");
                } else {
                    sortList.remove("황사");
                }
            }
        }) ;

        society1 = (CheckBox) findViewById(R.id.society1) ;
        society1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("감염병");
                } else {
                    sortList.remove("감염병");
                }
            }
        }) ;

        society2 = (CheckBox) findViewById(R.id.society2) ;
        society2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("미세먼지");
                } else {
                    sortList.remove("미세먼지");
                }
            }
        }) ;

        society3 = (CheckBox) findViewById(R.id.society3) ;
        society3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("화재");
                } else {
                    sortList.remove("화재");
                }
            }
        }) ;

        society4 = (CheckBox) findViewById(R.id.society4) ;
        society4.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("수질");
                } else {
                    sortList.remove("수질");
                }
            }
        }) ;

        society6 = (CheckBox) findViewById(R.id.society6) ;
        society6.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("위험물");
                } else {
                    sortList.remove("위험물");
                }
            }
        }) ;

        society7 = (CheckBox) findViewById(R.id.society7) ;
        society7.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("붕괴");
                } else {
                    sortList.remove("붕괴");
                }
            }
        }) ;

        society8 = (CheckBox) findViewById(R.id.society8) ;
        society8.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("교통사고");
                } else {
                    sortList.remove("교통사고");
                }
            }
        }) ;

        society9 = (CheckBox) findViewById(R.id.society9) ;
        society9.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("현장사고");
                } else {
                    sortList.remove("현장사고");
                }
            }
        }) ;

        society10 = (CheckBox) findViewById(R.id.society10) ;
        society10.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("자원수급");
                } else {
                    sortList.remove("자원수급");
                }
            }
        }) ;

        society11 = (CheckBox) findViewById(R.id.society11) ;
        society11.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("통신");
                } else {
                    sortList.remove("통신");
                }
            }
        }) ;

        society12 = (CheckBox) findViewById(R.id.society12) ;
        society12.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    sortList.add("우주");
                } else {
                    sortList.remove("우주");
                }
            }
        }) ;

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryPostAlarmInfo();
            }
        });


    }

    private void tryPostAlarmInfo() {
        showProgressDialog();

        final AlarmService alarmService = new AlarmService(this);
        alarmService.postAlarm(new AlarmInfo(sortList,sSharedPreferences.getLong("USER_IDX", -1)));
    }

    @Override
    public void validateAlarmSuccess(ReportResponse response) {
        hideProgressDialog();
        Log.d("확인","알람등록 성공");
        finish();
    }

    @Override
    public void validateSwitchSuccess(ReportResponse response) {

    }

    @Override
    public void validateDongSuccess(AddressGetResponse response) {



    }

    @Override
    public void validateFailure(String message) {

    }
}