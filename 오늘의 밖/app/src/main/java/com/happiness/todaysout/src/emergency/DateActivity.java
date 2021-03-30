package com.happiness.todaysout.src.emergency;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.emergency.Adapter.EmergencyAdapter;
import com.happiness.todaysout.src.emergency.Interface.JActivityView;
import com.happiness.todaysout.src.emergency.models.JInfo;
import com.happiness.todaysout.src.emergency.models.JResponse;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class DateActivity extends BaseActivity implements JActivityView {


    TextView textDate;
    TextView day;
    TextView textCount;
    RecyclerView rc_day;
    LinearLayoutManager layoutManager;
    EmergencyAdapter mEmergencyAdapter;
    String gu;
    ArrayList<JInfo> jList = new ArrayList<>();
    int total;
    String date;
    String month;
    String day1;
    ImageView btn_b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        btn_b = findViewById(R.id.btn_b);
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent notice = getIntent();
        date = notice.getStringExtra("date");
        month = notice.getStringExtra("month");
        day1 = notice.getStringExtra("day");
        gu = notice.getStringExtra("gu");

        Log.d("확인","aa"+date+month+day1+gu);


        textDate = findViewById(R.id.textDate);
        day = findViewById(R.id.day);
        textCount = findViewById(R.id.textCount);
        rc_day = findViewById(R.id.rc_day);


        textDate.setText(date);



        main();
        tryGetJInfo(Integer.parseInt(month),Integer.parseInt(day1));


    }

    private void tryGetJInfo(int month, int day) {
        showProgressDialog();
        final JService jService = new JService(this);
        jService.getJ(sSharedPreferences.getLong("USER_IDX", -1),month,day,gu,"서울특별시");
    }

    private void main() {
        layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_day.setLayoutManager(layoutManager);

        mEmergencyAdapter = new EmergencyAdapter(this, jList);
        rc_day.setAdapter(mEmergencyAdapter);
    }


    @Override
    public void validateJSuccess(JResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {

            Log.d("확인","재난조회 성공"+response.getCode());
            switch (response.getCode()) {

                case 1303:
                    jList.clear(); //먼저 eventList에 있는 것들을 다 지운다.


                    total = response.getResult().getTotal();

                    textCount.setText( Integer.toString(total));


                    if (response.getResult().getJlist() != null) {

                        jList.addAll(response.getResult().getJlist());

                    }

                    mEmergencyAdapter.notifyDataSetChanged(); //view차원에서 업데이트
                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateFailure(String message) {

    }
}