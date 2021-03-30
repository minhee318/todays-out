package com.softsquared.todaysout.src.mypage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.ApplicationClass;
import com.softsquared.todaysout.src.BaseActivity;
import com.softsquared.todaysout.src.mypage.Interface.MyView;
import com.softsquared.todaysout.src.mypage.model.MyDongResponse;
import com.softsquared.todaysout.src.mypage.model.MyResponse;
import com.softsquared.todaysout.src.mypage.model.PatchProfileResponse;
import com.softsquared.todaysout.src.weather.models.DongInfo;
import com.softsquared.todaysout.src.weather.models.RegisterInfo;
import com.softsquared.todaysout.src.weather.models.ReportResponse;

import java.util.ArrayList;

import static com.softsquared.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.softsquared.todaysout.src.ApplicationClass.FIRST_DONG;
import static com.softsquared.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.softsquared.todaysout.src.ApplicationClass.SECOND_DONG;
import static com.softsquared.todaysout.src.ApplicationClass.sSharedPreferences;

public class DongSettingActivity extends BaseActivity implements MyView {

    ArrayList<Long> list = new ArrayList<>();
    String firstgu;
    String secondgu;
    ImageView back;
    ArrayList<DongInfo> dongList2 = new ArrayList<>();
    ArrayList<String> items2 = new ArrayList<>();

    ArrayList<DongInfo> dongList3 = new ArrayList<>();
    ArrayList<String> items3 = new ArrayList<>();
    Spinner spinner1;
    Spinner spinner2;
    TextView text_guName1;
    TextView text_guName2;
    String dong1;
    String dong2;
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_setting);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);


        items2.add("동을 선택해주세요");
        items3.add("동을 선택해주세요");

        Intent intent = getIntent();
        list = (ArrayList<Long>) intent.getSerializableExtra("List");
        firstgu = intent.getStringExtra("firstgu");
        secondgu = intent.getStringExtra("secondgu");


        text_guName1 = findViewById(R.id.text_guName1);
        text_guName2 = findViewById(R.id.text_guName2);

        text_guName1.setText(firstgu);
        text_guName2.setText(secondgu);

        tryGetMyDongInfo(list.get(0));
        tryGetMyDongInfo2(list.get(1));


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items2);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                dong1 = items2.get(i);
                Log.d("확인", dong1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items3);

        adapter2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                dong2 = items3.get(i);
                Log.d("확인", dong2);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sSharedPreferences.edit();

                if (dong1.equals("동을 선택해주세요")) {
                } else {
                    tryPostMyDongInfo(list.get(0), dong1);
                    editor.putString(FIRST_DONG, dong1);
                }

                if (dong2.equals("동을 선택해주세요")) {

                } else {
                    tryPostMyDongInfo2(list.get(1), dong2);
                    editor.putString(SECOND_DONG, dong2);
                }


                editor.apply();
            }
        });


    }


    private void tryGetMyDongInfo(Long addressIdx) {
        showProgressDialog();

        final MyService myService = new MyService(this);
        myService.getMyDong(addressIdx);
    }


    private void tryGetMyDongInfo2(Long addressIdx) {
        showProgressDialog();

        final MyService myService = new MyService(this);
        myService.getMyDong2(addressIdx);
    }


    private void tryPostMyDongInfo(Long addressIdx, String dong) {
        showProgressDialog();

        final MyService myService = new MyService(this);
        myService.postMyDong(addressIdx, new RegisterInfo(dong));
    }

    private void tryPostMyDongInfo2(Long addressIdx, String dong) {
        showProgressDialog();

        final MyService myService = new MyService(this);
        myService.postMyDong2(addressIdx, new RegisterInfo(dong));
    }


    @Override
    public void validateMySuccess(MyResponse response) {

    }

    @Override
    public void validatePatchProfileSuccess(PatchProfileResponse response) {

    }

    @Override
    public void validateMyDongSuccess(MyDongResponse response) {
        hideProgressDialog();

        Log.d("확인", "동 조회 성공" + response.getCode());
        if (response.getIsSuccess()) {


            switch (response.getCode()) {
                case 1210:
                    dongList2.clear(); //먼저 eventList에 있는 것들을 다 지운다.

                    dongList2.addAll(response.getResult());

                    for (int i = 0; i < dongList2.size(); i++) {
                        items2.add(dongList2.get(i).getDong());
                    }
                    Log.d("확인", "items2의 사이즈:"+items2.size());

                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateMyDong2Success(MyDongResponse response) {
        hideProgressDialog();

        Log.d("확인", "동 조회 성공" + response.getCode());
        if (response.getIsSuccess()) {


            switch (response.getCode()) {
                case 1210:
                    dongList3.clear(); //먼저 eventList에 있는 것들을 다 지운다.


                    dongList3.addAll(response.getResult());

                    for (int i = 0; i < dongList3.size(); i++) {
                        items3.add(dongList3.get(i).getDong());
                    }

                    Log.d("확인", "items3의 사이즈:"+items3.size());

                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateMyPostDongSuccess(ReportResponse response) {
        Log.d("확인", "첫번째 동 등록 성공");
    }

    @Override
    public void validateMyPostDong2Success(ReportResponse response) {
        Log.d("확인", "두번째 동 등록 성공");
    }

    @Override
    public void validateByeSuccess(ReportResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }
}