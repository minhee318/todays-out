package com.happiness.todaysout.src.mypage;

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

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.mypage.Interface.MyView;
import com.happiness.todaysout.src.mypage.model.MyDongResponse;
import com.happiness.todaysout.src.mypage.model.MyResponse;
import com.happiness.todaysout.src.mypage.model.PatchProfileResponse;
import com.happiness.todaysout.src.weather.models.DongInfo;
import com.happiness.todaysout.src.weather.models.RegisterInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_DONG;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_DONG;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

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


        items2.add("?????? ??????????????????");
        items3.add("?????? ??????????????????");

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
                Log.d("??????", dong1);


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
                Log.d("??????", dong2);


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

                if (dong1.equals("?????? ??????????????????")) {
                } else {
                    tryPostMyDongInfo(list.get(0), dong1);
                    editor.putString(FIRST_DONG, dong1);
                }

                if (dong2.equals("?????? ??????????????????")) {

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

        Log.d("??????", "??? ?????? ??????" + response.getCode());
        if (response.getIsSuccess()) {


            switch (response.getCode()) {
                case 1210:
                    dongList2.clear(); //?????? eventList??? ?????? ????????? ??? ?????????.

                    dongList2.addAll(response.getResult());

                    for (int i = 0; i < dongList2.size(); i++) {
                        items2.add(dongList2.get(i).getDong());
                    }
                    Log.d("??????", "items2??? ?????????:"+items2.size());

                    break;
            }
        } else {


            showCustomToast("???????????? ????????? ???????????? ????????????.");

        }
    }

    @Override
    public void validateMyDong2Success(MyDongResponse response) {
        hideProgressDialog();

        Log.d("??????", "??? ?????? ??????" + response.getCode());
        if (response.getIsSuccess()) {


            switch (response.getCode()) {
                case 1210:
                    dongList3.clear(); //?????? eventList??? ?????? ????????? ??? ?????????.


                    dongList3.addAll(response.getResult());

                    for (int i = 0; i < dongList3.size(); i++) {
                        items3.add(dongList3.get(i).getDong());
                    }

                    Log.d("??????", "items3??? ?????????:"+items3.size());

                    break;
            }
        } else {


            showCustomToast("???????????? ????????? ???????????? ????????????.");

        }
    }

    @Override
    public void validateMyPostDongSuccess(ReportResponse response) {
        hideProgressDialog();
        Log.d("??????", "????????? ??? ?????? ??????");
    }

    @Override
    public void validateMyPostDong2Success(ReportResponse response) {
        hideProgressDialog();
        Log.d("??????", "????????? ??? ?????? ??????");
    }

    @Override
    public void validateByeSuccess(ReportResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }
}