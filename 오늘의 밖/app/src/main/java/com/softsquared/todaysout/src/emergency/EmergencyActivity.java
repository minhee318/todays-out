package com.softsquared.todaysout.src.emergency;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.BaseActivity;
import com.softsquared.todaysout.src.emergency.Adapter.EmergencyAdapter;
import com.softsquared.todaysout.src.emergency.Interface.JActivityView;
import com.softsquared.todaysout.src.emergency.models.EmergencyInfo;
import com.softsquared.todaysout.src.emergency.models.JInfo;
import com.softsquared.todaysout.src.emergency.models.JResponse;
import com.softsquared.todaysout.src.main.MainActivity;
import com.softsquared.todaysout.src.mypage.MyPageActivity;
import com.softsquared.todaysout.src.weather.Adapter.NoticeBoardAdapter;
import com.softsquared.todaysout.src.weather.Adapter.RankingAdapter;
import com.softsquared.todaysout.src.weather.Adapter.TodayWeaterAdapter;
import com.softsquared.todaysout.src.weather.Adapter.WeekWeaterAdapter;
import com.softsquared.todaysout.src.weather.MyListDecoration;

import com.softsquared.todaysout.src.weather.models.NoticeBoardInfo;
import com.softsquared.todaysout.src.weather.models.RankingInfo;
import com.softsquared.todaysout.src.weather.models.TodayWeatherInfo;
import com.softsquared.todaysout.src.weather.models.WeekWeatherInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.softsquared.todaysout.src.ApplicationClass.sSharedPreferences;

public class EmergencyActivity extends BaseActivity implements View.OnClickListener, JActivityView {

    RecyclerView rc_emergency;

    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManager2;
    LinearLayoutManager layoutManager3;
    LinearLayoutManager layoutManager4;

    ArrayList<JInfo> jList = new ArrayList<>();

    ArrayList<NoticeBoardInfo> noticeList = new ArrayList<>();
    ArrayList<RankingInfo> rankList = new ArrayList<>();
    EmergencyAdapter mEmergencyAdapter;
    ImageView btn_backemergency;
    ScrollView emergencyScroll;

    LinearLayout LL_slideup;
    TextView text_first; //닫았을 때 말풍선
    LinearLayout open;
    LinearLayout close;
    LinearLayout LL_heart;
    RecyclerView rc_noticeBoard;
    NoticeBoardAdapter mNoticeBoardAdapter;
    RankingAdapter mRankingAdapter;
    ImageView btn_calender;
    FloatingActionButton fdFab;

    TabLayout tablayout;
    RecyclerView rc_heart;

    String gu;

    TextView textgu1;
    TextView textgu2;
    TextView textgu3;

    String month;
    String day;

    int total;

    TextView textCount;
    TextView textCount2;
    ImageView icon;
    ImageView icon2;
    TextView textGu4;







    FrameLayout FL_content;
    private BottomSheetBehavior mBottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        textCount = findViewById(R.id.textCount);
        textCount2 = findViewById(R.id.textCount2);
        icon = findViewById(R.id.icon);
        icon2 = findViewById(R.id.icon2);



        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());

        month = monthFormat.format(currentTime);
        day = dayFormat.format(currentTime);


        Intent notice = getIntent();
        gu = notice.getStringExtra("gu");


        textgu1 = findViewById(R.id.textgu1);
        textgu2 = findViewById(R.id.textgu2);
        textgu3 = findViewById(R.id.textgu3);
        textGu4 = findViewById(R.id.textGu4);

        textgu1.setText(gu);
        textgu2.setText(gu);
        textgu3.setText(gu);
        textGu4.setText(gu);




        FL_content = findViewById(R.id.FL_content);

        fdFab = findViewById(R.id.fdFab);

        tablayout = findViewById(R.id.tablayout);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //탭이 선택되었을 때

                int pos = tab.getPosition();
                if (pos == 0) {
                    rc_noticeBoard.setVisibility(View.VISIBLE);
                    rc_heart.setVisibility(View.INVISIBLE);
                    LL_heart.setVisibility(View.GONE);
                    fdFab.show();

                } else if (pos == 1) {
                    rc_heart.setVisibility(View.VISIBLE);
                    rc_noticeBoard.setVisibility(View.INVISIBLE);
                    LL_heart.setVisibility(View.VISIBLE);
                    fdFab.hide();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //탭이 선택되지 않았을 때
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //탭이 다시 선택되었을 때
            }
        });

        rc_heart = findViewById(R.id.rc_heart);

        LL_heart = findViewById(R.id.LL_heart);

        open = findViewById(R.id.open);
        close = findViewById(R.id.close);

        rc_noticeBoard = findViewById(R.id.rc_noticeBoard);

        emergencyScroll = findViewById(R.id.emergencyScroll);
        LL_slideup = findViewById(R.id.LL_slideup);

        text_first = findViewById(R.id.text_first_ment2);

        btn_calender = findViewById(R.id.btn_calender);
        btn_calender.setOnClickListener(this);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED://전부 내렸을 때
                       // showCustomToast("Collapsed");
                        open.setVisibility(View.INVISIBLE);
                        close.setVisibility(View.VISIBLE);
                        fdFab.hide();
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                       // showCustomToast("Dragging...");
                        emergencyScroll.setVisibility(View.VISIBLE);
                        LL_slideup.setVisibility(View.INVISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED://전부 올렸을 때
                       // showCustomToast("Expanded");
                        emergencyScroll.scrollTo(0, 0);//bottomsheet 올리자마자 스크롤 맨 위로 올려놓기
                        emergencyScroll.setVisibility(View.INVISIBLE);
                        LL_slideup.setVisibility(View.VISIBLE);
                        open.setVisibility(View.VISIBLE);
                        close.setVisibility(View.INVISIBLE);
                        fdFab.show();
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                       // showCustomToast("Settling");
                        break;

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
               // showCustomToast("Sliding..");

            }
        });



        rc_emergency = findViewById(R.id.rc_emergency);
        btn_backemergency = findViewById(R.id.btn_backemergency);
        btn_backemergency.setOnClickListener(this);



//        eList.add(new EmergencyInfo("[중랑구청] 1005번 추가 확진자 발생. \n" +
//                "1003~1005번 확진자 역학 조사 완료. \n" +
//                "세부내용 홈페이지, blog. naver.com/\n" +
//                "jung-nang-gu 참조바랍니다.","10분 전"));
//        eList.add(new EmergencyInfo("[중랑구청] 1005번 추가 확진자 발생. \n" +
//                "1003~1005번 확진자 역학 조사 완료. \n" +
//                "세부내용 홈페이지, blog. naver.com/\n" +
//                "jung-nang-gu 참조바랍니다.","10분 전"));
//        eList.add(new EmergencyInfo("[중랑구청] 1005번 추가 확진자 발생. \n" +
//                "1003~1005번 확진자 역학 조사 완료. \n" +
//                "세부내용 홈페이지, blog. naver.com/\n" +
//                "jung-nang-gu 참조바랍니다.","10분 전"));
//        eList.add(new EmergencyInfo("[중랑구청] 1005번 추가 확진자 발생. \n" +
//                "1003~1005번 확진자 역학 조사 완료. \n" +
//                "세부내용 홈페이지, blog. naver.com/\n" +
//                "jung-nang-gu 참조바랍니다.","10분 전"));

        noticeList.add(new NoticeBoardInfo("준","신내동","10분전","오늘 너무 춥네요!",2,6));
        noticeList.add(new NoticeBoardInfo("준","신내동","10분전","오늘 너무 춥네요!",2,6));
        noticeList.add(new NoticeBoardInfo("준","신내동","10분전","오늘 너무 춥네요!",2,6));
        noticeList.add(new NoticeBoardInfo("준","신내동","10분전","오늘 너무 춥네요!",2,6));
        noticeList.add(new NoticeBoardInfo("준","신내동","10분전","오늘 너무 춥네요!",2,6));


        rankList.add(new RankingInfo(17,"비누","12:43","오늘 생각보다 더워요!","신내동"));
        rankList.add(new RankingInfo(17,"비누","12:43","오늘 생각보다 더워요!","신내동"));
        rankList.add(new RankingInfo(17,"비누","12:43","오늘 생각보다 더워요!","신내동"));
        rankList.add(new RankingInfo(17,"비누","12:43","오늘 생각보다 더워요!","신내동"));
        rankList.add(new RankingInfo(17,"비누","12:43","오늘 생각보다 더워요!","신내동"));


        main();

//        tryGetJInfo(03,27);
        tryGetJInfo(Integer.parseInt(month),Integer.parseInt(day));


        noticeBoard();
        ranking();

    }



    private void tryGetJInfo(int month, int day) {
        showProgressDialog();
        final JService jService = new JService(this);
        jService.getJ(sSharedPreferences.getLong("USER_IDX", -1),month,day,gu,"서울특별시");
    }

    private void noticeBoard(){
        layoutManager3 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_noticeBoard.setLayoutManager(layoutManager3);

//        mNoticeBoardAdapter = new NoticeBoardAdapter(this,noticeList);
        rc_noticeBoard.setAdapter(mNoticeBoardAdapter);
    }

    private void ranking() {
        layoutManager4 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_heart.setLayoutManager(layoutManager4);

       // mRankingAdapter = new RankingAdapter(this,rankList);
        rc_heart.setAdapter(mRankingAdapter);
    }


    private void main() {
        layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_emergency.setLayoutManager(layoutManager);

        mEmergencyAdapter = new EmergencyAdapter(this, jList);
        rc_emergency.setAdapter(mEmergencyAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_backemergency:
                finish();
                break;

            case R.id.btn_calender:
                Intent mypage = new Intent(this, MonthActivity.class);
                mypage.putExtra("gu",gu);
                startActivity(mypage);


                break;

        }
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
                    textCount2.setText( Integer.toString(total));


                    if (response.getResult().getJlist() != null) {

                        jList.addAll(response.getResult().getJlist());


                        if(response.getResult().getJlist().get(0).getKinds().equals("지진")){
                            icon.setImageResource(R.drawable.earthquake);
                            icon2.setImageResource(R.drawable.earthquake_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("태풍")){
                            icon.setImageResource(R.drawable.typhoon2);
                            icon2.setImageResource(R.drawable.typhoon_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("해일")){
                            icon.setImageResource(R.drawable.natural3);
                            icon2.setImageResource(R.drawable.tsunami_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("홍수")){
                            icon.setImageResource(R.drawable.flood);
                            icon2.setImageResource(R.drawable.flood_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("호우")){
                            icon.setImageResource(R.drawable.downpour);
                            icon2.setImageResource(R.drawable.downpour_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("강풍")){
                            icon.setImageResource(R.drawable.gale);
                            icon2.setImageResource(R.drawable.gale_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("대설")){
                            icon.setImageResource(R.drawable.heavy_snow);
                            icon2.setImageResource(R.drawable.heavy_snow_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("한파")){
                            icon.setImageResource(R.drawable.cold);
                            icon2.setImageResource(R.drawable.cold_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("폭염")){
                            icon.setImageResource(R.drawable.hot);
                            icon2.setImageResource(R.drawable.hot_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("건조")){
                            icon.setImageResource(R.drawable.dry);
                            icon2.setImageResource(R.drawable.dry_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("황사")){
                            icon.setImageResource(R.drawable.dust_storm);
                            icon.setImageResource(R.drawable.dust_storm_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("민방공")){
                            icon.setImageResource(R.drawable.war);
                            icon2.setImageResource(R.drawable.war_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("테러")){
                            icon.setImageResource(R.drawable.terror);
                            icon2.setImageResource(R.drawable.terror_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("방사능")){
                            icon.setImageResource(R.drawable.radiation);
                            icon2.setImageResource(R.drawable.radiation_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("감염병")){
                            icon.setImageResource(R.drawable.virus_four);
                            icon2.setImageResource(R.drawable.virus_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("미세먼지")){
                            icon.setImageResource(R.drawable.dust);
                            icon2.setImageResource(R.drawable.dust_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("화재")){
                            icon.setImageResource(R.drawable.fire);
                            icon2.setImageResource(R.drawable.fire_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("수질")){
                            icon.setImageResource(R.drawable.water);
                            icon2.setImageResource(R.drawable.water_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("위험물")){
                            icon.setImageResource(R.drawable.danger);
                            icon2.setImageResource(R.drawable.danger_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("붕괴")){
                            icon.setImageResource(R.drawable.collapse);
                            icon2.setImageResource(R.drawable.collapse_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("교통사고")){
                            icon.setImageResource(R.drawable.traffic_accident);
                            icon2.setImageResource(R.drawable.traffic_accident_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("현장사고")){
                            icon.setImageResource(R.drawable.construct_accident);
                            icon2.setImageResource(R.drawable.construct_accident_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("자원수급")){
                            icon.setImageResource(R.drawable.resource);
                            icon.setImageResource(R.drawable.resource_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("통신")){
                            icon.setImageResource(R.drawable.communication);
                            icon.setImageResource(R.drawable.communication_quarter);
                        }else if(response.getResult().getJlist().get(0).getKinds().equals("우주")){
                            icon.setImageResource(R.drawable.space);
                            icon.setImageResource(R.drawable.space_quarter);
                        }

                    }else{
                        Log.d("확인","배열이 null이다.");
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