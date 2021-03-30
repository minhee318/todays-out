package com.softsquared.todaysout.src.alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.BaseActivity;
import com.softsquared.todaysout.src.alarm.Interface.AlarmActivityView;
import com.softsquared.todaysout.src.alarm.model.AlarmInfo;
import com.softsquared.todaysout.src.main.MainActivity;
import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.weather.models.ReportResponse;

import static com.softsquared.todaysout.src.ApplicationClass.sSharedPreferences;

public class AlarmActivity extends BaseActivity implements View.OnClickListener, AlarmActivityView {

    ImageView img_filter;
    Switch chatting;
    Switch jenan;
    Switch deviceSwitch;
    ImageView switchOn;
    LinearLayout LL_de;
    boolean check1 = false;
    boolean check2 = false;
    String firstgu;
    String secondgu;
    String firstDong;
    String secondDong;
    TextView firstGu;
    TextView secondGu;
    TextView firstDong1;
    TextView secondDong1;
    ImageView back;

    @Override
    protected void onResume() {
        super.onResume();


        if(NotificationManagerCompat.from(this).areNotificationsEnabled()==true){
            deviceSwitch.setChecked(true);
        }else deviceSwitch.setChecked(false);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        firstGu = findViewById(R.id.firstGu);
        secondGu = findViewById(R.id.secondGu);
        firstDong1 = findViewById(R.id.firstDong);
        secondDong1 = findViewById(R.id.secondDong);

        img_filter = findViewById(R.id.img_filter);
        img_filter.setOnClickListener(this);

        switchOn = findViewById(R.id.switchOn);
        LL_de = findViewById(R.id.LL_de);

        chatting = findViewById(R.id.chatting);
        jenan = findViewById(R.id.jenan);
        deviceSwitch = findViewById(R.id.deviceSwitch);

        getData();


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        if(NotificationManagerCompat.from(this).areNotificationsEnabled()==true){
            deviceSwitch.setChecked(true);
        }else deviceSwitch.setChecked(false);



        deviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    //체크 되었을 때 알림설정 화면으로 이동
                    Intent intent = new Intent();
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        String channelId = "one-channel";
                        String channelName = "My Channel One";
                        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                    }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                        intent.putExtra("app_package", getPackageName());
                        intent.putExtra("app_uid", getApplicationInfo().uid);
                    }else{
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                    }

                    startActivity(intent);


                } else {
                    //해제 되었을 때
                    Intent intent = new Intent();
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        String channelId = "one-channel";
                        String channelName = "My Channel One";
                        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                    }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                        intent.putExtra("app_package", getPackageName());
                        intent.putExtra("app_uid", getApplicationInfo().uid);
                    }else{
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                    }

                    startActivity(intent);


                }

            }
        });



        tryGetAddressInfo();

        chatting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // TODO Auto-generated method stub

                if (isChecked) {//활성화


                    if (check2) {
                        tryPostSwitchInfo(true, true);
                    } else {
                        tryPostSwitchInfo(true, false);
                    }

                    check1 = true;

                    switchOn.setVisibility(View.INVISIBLE);
                    LL_de.setVisibility(View.VISIBLE);


                } else {//비활성화

                    if (check2) {
                        tryPostSwitchInfo(false, true);
                    } else {
                        tryPostSwitchInfo(false, false);
                        switchOn.setVisibility(View.VISIBLE);
                        LL_de.setVisibility(View.INVISIBLE);
                    }

                    check1 = false;


                }


            }

        });


        jenan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // TODO Auto-generated method stub

                if (isChecked) {//활성화

                    if (check1) {
                        tryPostSwitchInfo(true, true);
                    } else {
                        tryPostSwitchInfo(false, true);
                    }

                    check2 = true;
                    saveData();

                    switchOn.setVisibility(View.INVISIBLE);
                    LL_de.setVisibility(View.VISIBLE);


                } else {//비활성화

                    if (check1) {
                        tryPostSwitchInfo(true, false);


                    } else {
                        tryPostSwitchInfo(false, false);
                        switchOn.setVisibility(View.VISIBLE);
                        LL_de.setVisibility(View.INVISIBLE);
                    }

                    check2 = false;
                    saveData();

                }


            }

        });





    }


    private void tryPostSwitchInfo(boolean notice, boolean disaster) {

        final AlarmService alarmService = new AlarmService(this);
        alarmService.postSwitch(notice, disaster);
    }


    private void tryGetAddressInfo() {
        showProgressDialog();

        final AlarmService alarmService = new AlarmService(this);
        alarmService.getAddress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_filter:
                Intent filter = new Intent(AlarmActivity.this, AlarmInfoActivity.class);
                startActivity(filter);
                break;
        }
    }

    @Override
    public void validateAlarmSuccess(ReportResponse response) {

    }

    @Override
    public void validateSwitchSuccess(ReportResponse response) {
        Log.d("확인", "스위치성공");
    }

    @Override
    public void validateDongSuccess(AddressGetResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1202:

                    firstgu = response.getResult().get(0).getGu();
                    secondgu = response.getResult().get(1).getGu();

                    firstDong = response.getResult().get(0).getDong();
                    secondDong = response.getResult().get(1).getDong();

                    firstGu.setText(firstgu);
                    secondGu.setText(secondgu);
                    firstDong1.setText(firstDong);
                    secondDong1.setText(secondDong);


                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateFailure(String message) {

    }



    private void getData() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);


        check1 = pref.getBoolean("notice", false);
        check2 = pref.getBoolean("jenan", false);


        if(check1){
            chatting.setChecked(true);
            switchOn.setVisibility(View.INVISIBLE);
            LL_de.setVisibility(View.VISIBLE);
        }else{
            chatting.setChecked(false);

        }



        if(check2){
            jenan.setChecked(true);
            switchOn.setVisibility(View.INVISIBLE);
            LL_de.setVisibility(View.VISIBLE);
        }else{
            jenan.setChecked(false);

        }


    }

    private void saveData() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        //SharedPreferences에 각 아이디를 지정하고 EditText 내용을 저장한다.

        editor.putBoolean("notice", check1);
        editor.putBoolean("jenan", check2);

        // TODO : 필수 없으면 저장안됨
        editor.commit();

    }


}