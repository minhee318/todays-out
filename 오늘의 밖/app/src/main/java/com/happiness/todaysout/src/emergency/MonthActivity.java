package com.happiness.todaysout.src.emergency;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.emergency.Adapter.CalendarAdapter;
import com.happiness.todaysout.src.emergency.Util.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthActivity extends BaseActivity implements View.OnClickListener{
    public int mCenterPosition;
    private long mCurrentTime;
    public ArrayList<Object>mCalendarList = new ArrayList<>();
    private RecyclerView calendar;
    private CalendarAdapter mCalendarAdapter;
    private StaggeredGridLayoutManager manager;
    TextView date;
    ImageView test;
    ImageView btn_calenderback;
    String gu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        Intent notice = getIntent();
        gu = notice.getStringExtra("gu");

        btn_calenderback = findViewById(R.id.btn_calenderback);
      btn_calenderback.setOnClickListener(this);

        test = findViewById(R.id.test); //현재 날짜로 돌아온다.
        test.setOnClickListener(this);

        calendar = findViewById(R.id.calendar);

        date = findViewById(R.id.date);

        initCalendarList();

        setRecycler();

    }

    private void setRecycler(){
        if(mCalendarList == null){
            Log.d("확인","리사이클러뷰가 정의되지 않았습니다.");
        }

        manager = new StaggeredGridLayoutManager(7,StaggeredGridLayoutManager.VERTICAL);

        mCalendarAdapter = new CalendarAdapter(mCalendarList,gu);

        mCalendarAdapter.setCalendarList(mCalendarList);
        calendar.setLayoutManager(manager);
        calendar.setAdapter(mCalendarAdapter);

        if(mCenterPosition >= 0){
            calendar.scrollToPosition(mCenterPosition);
        }

    }



    private void initCalendarList() {
        GregorianCalendar cal = new GregorianCalendar();
        setCalendarList(cal);
    }

    private void setCalendarList(GregorianCalendar cal) {

        setTitle(Integer.toString((int) cal.getTimeInMillis()));


        ArrayList<Object> calendarList  = new ArrayList<>();

        for (int i = -300; i < 300; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
                if (i == 0) {
                    mCenterPosition = calendarList.size();
                }

                // 타이틀인듯
                calendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월에 마지막 요일

                // EMPTY 생성
                for (int j = 0; j < dayOfWeek; j++) {
                    calendarList.add(Keys.EMPTY);
                }
                for (int j = 1; j <= max; j++) {
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
                }

                // TODO : 결과값 넣을떄 여기다하면될듯

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mCalendarList = calendarList;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_calenderback:
                finish();
                break;

            case R.id.test:
                setRecycler();
                break;

        }
    }
}