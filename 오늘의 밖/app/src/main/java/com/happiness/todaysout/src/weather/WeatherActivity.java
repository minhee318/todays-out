package com.happiness.todaysout.src.weather;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.weather.Adapter.NoticeBoardAdapter;
import com.happiness.todaysout.src.weather.Adapter.RankingAdapter;
import com.happiness.todaysout.src.weather.Adapter.TodayWeaterAdapter;
import com.happiness.todaysout.src.weather.Adapter.WeekWeaterAdapter;
import com.happiness.todaysout.src.weather.interfaces.WeatherActivityView;
import com.happiness.todaysout.src.weather.models.BoardInfo;
import com.happiness.todaysout.src.weather.models.BoardPostResponse;
import com.happiness.todaysout.src.weather.models.BoardResponse;
import com.happiness.todaysout.src.weather.models.CommentResponse;
import com.happiness.todaysout.src.weather.models.DetailResponse;
import com.happiness.todaysout.src.weather.models.DongResponse;
import com.happiness.todaysout.src.weather.models.DustResponse;
import com.happiness.todaysout.src.weather.models.HeartPostResponse;
import com.happiness.todaysout.src.weather.models.NowInfo;
import com.happiness.todaysout.src.weather.models.NowResponse;
import com.happiness.todaysout.src.weather.models.PatchResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;
import com.happiness.todaysout.src.weather.models.TodayResponse;
import com.happiness.todaysout.src.weather.models.UpDownResponse;
import com.happiness.todaysout.src.weather.models.WeekInfo;
import com.happiness.todaysout.src.weather.models.WeekResponse;

import java.util.ArrayList;

import static android.net.wifi.rtt.CivicLocationKeys.STATE;

public class WeatherActivity extends BaseActivity implements View.OnClickListener, WeatherActivityView {

    RecyclerView rc_today;
    RecyclerView rc_week;
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManager2;
    LinearLayoutManager layoutManager3;
    LinearLayoutManager layoutManager4;
    ArrayList<NowInfo> todayList = new ArrayList<>();
    ArrayList<WeekInfo> weekList = new ArrayList<>();
    ArrayList<BoardInfo> noticeList = new ArrayList<>();
    ArrayList<BoardInfo> rankList = new ArrayList<>();
    TodayWeaterAdapter mTodayWeaterAdapter;
    WeekWeaterAdapter mWeekWeaterAdapter;
    ImageView btn_backweather;
    ScrollView weatherScroll;
    LinearLayout LL_slideup;
    TextView text_first; //닫았을 때 말풍선
    LinearLayout open;
    LinearLayout close;
    LinearLayout LL_heart;
    RecyclerView rc_noticeBoard;
    NoticeBoardAdapter mNoticeBoardAdapter;
    RankingAdapter mRankingAdapter;
    TextView btn_cloth;
    FloatingActionButton fdFab;
    TextView textUp;
    TextView textDown;
    TextView textNow;
    ImageView img_weather;
    TextView text_ment1;
    TextView text_ment2;
    ImageView img_quater;

    TabLayout tablayout;
    RecyclerView rc_heart;
    TextView text_nowOndo;
    Long addressIdx;
    ImageView img_superdust;
    ImageView img_dust;
    TextView textGu;
    TextView textGu2;
    TextView textGu3;
    TextView textGu4;
    Long userIdx;
    String firstContent;
    String firstHeartNum;
    TextView text_first_ment2;
    TextView text_heart_number;
    String gu;
    LinearLayout LL_weatherHeart;
    int page;
    int totalPage;


    FrameLayout FL_content;
    private BottomSheetBehavior mBottomSheetBehavior;


    @Override
    protected void onRestart() {



        super.onRestart();
    }

    @Override
    protected void onResume() {

        page = 0;
      tryGetBoardInfo(addressIdx,"recently",page);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        page = 0;

        LL_weatherHeart = findViewById(R.id.LL_weatherHeart);

        text_first_ment2 = findViewById(R.id.text_first_ment2);
        text_heart_number = findViewById(R.id.text_heart_number);

        img_quater = findViewById(R.id.img_quater);


        textGu = findViewById(R.id.textGu);
        textGu2 = findViewById(R.id.textGu2);
        textGu3 = findViewById(R.id.textGu3);
        textGu4 = findViewById(R.id.textGu4);

        img_dust = findViewById(R.id.img_dust);
        img_superdust = findViewById(R.id.img_superdust);

        Intent notice = getIntent();
        addressIdx = notice.getLongExtra("addressIdx", 0);

        Log.d("확인", "주소인덱스:" + addressIdx);



        text_nowOndo = findViewById(R.id.text_nowOndo);

        text_ment1 = findViewById(R.id.text_ment1);
        text_ment2 = findViewById(R.id.text_ment2);

        textNow = findViewById(R.id.textNow);
        img_weather = findViewById(R.id.img_weather);

        textUp = findViewById(R.id.textUp);
        textDown = findViewById(R.id.textDown);

        FL_content = findViewById(R.id.FL_content);

        fdFab = findViewById(R.id.fdFab);
        fdFab.setOnClickListener(this);

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
                    page = 0;
                    tryGetBoardInfo(addressIdx,"recently",page);

                } else if (pos == 1) {
                    rc_heart.setVisibility(View.VISIBLE);
                    rc_noticeBoard.setVisibility(View.INVISIBLE);
                    LL_heart.setVisibility(View.VISIBLE);
                    fdFab.hide();
                    tryGetBoardInfo(addressIdx, "heart",0);
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

        weatherScroll = findViewById(R.id.weatherScroll);
        LL_slideup = findViewById(R.id.LL_slideup);

        text_first = findViewById(R.id.text_first_ment2);

        btn_cloth = findViewById(R.id.btn_cloth);
        btn_cloth.setOnClickListener(this);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);




        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED://전부 내렸을 때
                        //showCustomToast("Collapsed");
                        open.setVisibility(View.INVISIBLE);
                        close.setVisibility(View.VISIBLE);
                        fdFab.hide();
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        //showCustomToast("Dragging...");
                        weatherScroll.setVisibility(View.VISIBLE);
                        LL_slideup.setVisibility(View.INVISIBLE);
                        page = 0;
                        tryGetBoardInfo(addressIdx,"recently",page);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED://전부 올렸을 때
                        // showCustomToast("Expanded");
                        weatherScroll.scrollTo(0, 0);//bottomsheet 올리자마자 스크롤 맨 위로 올려놓기
                        weatherScroll.setVisibility(View.INVISIBLE);
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
                //showCustomToast("Sliding..");

            }
        });


        rc_today = findViewById(R.id.rc_today);
        rc_week = findViewById(R.id.rc_week);
        btn_backweather = findViewById(R.id.btn_backweather);
        btn_backweather.setOnClickListener(this);


        today();
        week();


        ranking();


        tryGetNowInfo(addressIdx);
        tryGetDustInfo(addressIdx);
        tryGetTodayInfo(addressIdx);



        tryGetBoardInfo(addressIdx, "recently",page);


        tryGetUpDownInfo(addressIdx);

        tryGetWeekInfo(addressIdx);


        noticeBoard();

    }

    private void tryGetNowInfo(Long addressIdx) {
        showProgressDialog();
        final WeatherService weatherService = new WeatherService(this);
        weatherService.getNow(addressIdx);
    }

    private void tryGetUpDownInfo(Long addressIdx) {
        // showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.getUpDown(addressIdx);
    }

    private void tryGetDustInfo(Long addressIdx) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.getDust(addressIdx);
    }

    private void tryGetTodayInfo(Long addressIdx) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.getToday(addressIdx);
    }

    private void tryGetWeekInfo(Long addressIdx) {
        showProgressDialog();
        final WeatherService weatherService = new WeatherService(this);
        weatherService.getWeek(addressIdx);
    }

    private void tryGetBoardInfo(Long addressIdx, String sortType,int page) {
        showProgressDialog();
        final WeatherService weatherService = new WeatherService(this);
        weatherService.getBoard(addressIdx, sortType,page, "WEATHER");
    }


    private void today() {
        layoutManager2 = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
        rc_today.setLayoutManager(layoutManager2);
        mTodayWeaterAdapter = new TodayWeaterAdapter(this, todayList);
        rc_today.setAdapter(mTodayWeaterAdapter);

        MyListDecoration decoration = new MyListDecoration();
        rc_today.addItemDecoration(decoration);
    }

    private void week() {
        layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_week.setLayoutManager(layoutManager);

        mWeekWeaterAdapter = new WeekWeaterAdapter(this, weekList);
        rc_week.setAdapter(mWeekWeaterAdapter);
    }


    private void noticeBoard() {
        layoutManager3 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_noticeBoard.setLayoutManager(layoutManager3);

        mNoticeBoardAdapter = new NoticeBoardAdapter(this, noticeList,addressIdx);
        rc_noticeBoard.setAdapter(mNoticeBoardAdapter);

        rc_noticeBoard.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount();

                if(lastVisibleItemPosition+1 == itemTotalCount){
                    page++;
                    if(page <= totalPage){
                        tryGetBoardInfo(addressIdx, "recently",page);
                    }else {

                    }

                }
            }
        });

    }

    private void ranking() {
        layoutManager4 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_heart.setLayoutManager(layoutManager4);

        mRankingAdapter = new RankingAdapter(this, rankList);
        rc_heart.setAdapter(mRankingAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_backweather:
                finish();
                break;

            case R.id.fdFab:
                Intent write = new Intent(WeatherActivity.this, WriteActivity.class);
                write.putExtra("구정보", gu);
                write.putExtra("addressIdx", addressIdx);
                startActivity(write);
                break;


            case R.id.btn_cloth:
                final Dialog dlg = new Dialog(this);

                dlg.setCancelable(false);
                dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dlg.setContentView(R.layout.dialog_cloth);
                dlg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                dlg.show();
                dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {

                    }
                });


                final ImageView btn_cancel = (ImageView) dlg.findViewById(R.id.btn_cancel);


                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dlg.dismiss();
                    }
                });


                break;

        }
    }


    @Override
    public void validateUpDownSuccess(UpDownResponse response) {
        hideProgressDialog();

        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1204:
                    Log.d("확인", "최고,최저기온 성공");
                    textUp.setText(response.getResult().getUp());
                    textDown.setText(response.getResult().getDown());


                    break;
            }
        } else {
            Log.d("확인", response.getMessage());
            showCustomToast("네트워크 연결이 원활하지 않습니다.");


        }
    }

    @Override
    public void validateDustSuccess(DustResponse response) {
        hideProgressDialog();

        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1220:
                    Log.d("확인", "미세먼지 성공");

                    if (response.getResult().getDust().equals("1")) {
                        img_dust.setImageResource(R.drawable.dust_good);
                    } else if (response.getResult().getDust().equals("2")) {
                        img_dust.setImageResource(R.drawable.usually);
                    } else if (response.getResult().getDust().equals("3")) {
                        img_dust.setImageResource(R.drawable.little_bad);
                    } else if (response.getResult().getDust().equals("4")) {
                        img_dust.setImageResource(R.drawable.dust_bad);
                    }


                    if (response.getResult().getFineDust().equals("1")) {
                        img_superdust.setImageResource(R.drawable.dust_good);
                    } else if (response.getResult().getFineDust().equals("2")) {
                        img_superdust.setImageResource(R.drawable.usually);
                    } else if (response.getResult().getFineDust().equals("3")) {
                        img_superdust.setImageResource(R.drawable.little_bad);
                    } else if (response.getResult().getFineDust().equals("4")) {
                        img_superdust.setImageResource(R.drawable.dust_bad);
                    }

                    break;
            }
        } else {
            Log.d("확인", response.getMessage());
            showCustomToast("네트워크 연결이 원활하지 않습니다.");


        }
    }

    @Override
    public void validateTodaySuccess(TodayResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1205:
                    todayList.clear(); //먼저 eventList에 있는 것들을 다 지운다.

                    todayList.addAll(response.getResult());

                    mTodayWeaterAdapter.notifyDataSetChanged(); //view차원에서 업데이트
                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateDetailSuccess(DetailResponse response) {

    }

    @Override
    public void validateCommentSuccess(CommentResponse response) {

    }

    @Override
    public void validateHeartSuccess(HeartPostResponse response) {

    }

    @Override
    public void validateHeartGetSuccess(HeartPostResponse response) {

    }

    @Override
    public void validateContentSuccess(BoardPostResponse response) {

    }

    @Override
    public void validateReportSuccess(ReportResponse response) {

    }

    @Override
    public void validatePostCommentSuccess(ReportResponse response) {

    }

    @Override
    public void validatePatchSuccess(PatchResponse response) {

    }

    @Override
    public void validateDongSuccess(DongResponse response) {

    }

    @Override
    public void validateWeekSuccess(WeekResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1225:
                    weekList.clear(); //먼저 eventList에 있는 것들을 다 지운다.

                    weekList.addAll(response.getResult());

                    mWeekWeaterAdapter.notifyDataSetChanged(); //view차원에서 업데이트
                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateHeartSuccess(BoardResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1215:
                    Log.d("확인", "하트순위 성공");
                    rankList.clear(); //먼저 eventList에 있는 것들을 다 지운다.

                    if (response.getResult().getMsgList().size() > 5) {
                        rankList.add(response.getResult().getMsgList().get(0));
                        rankList.add(response.getResult().getMsgList().get(1));
                        rankList.add(response.getResult().getMsgList().get(2));
                        rankList.add(response.getResult().getMsgList().get(3));
                        rankList.add(response.getResult().getMsgList().get(4));
                    } else {
                        rankList.addAll(response.getResult().getMsgList());
                    }


                    mRankingAdapter.notifyDataSetChanged(); //view차원에서 업데이트
                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateRegisterSuccess(ReportResponse response) {

    }

    @Override
    public void validateBoardSuccess(BoardResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1216:
                    Log.d("확인", "게시판 성공");

                    if(page == 0){
                        noticeList.clear(); //먼저 eventList에 있는 것들을 다 지운다.
                    }
//

                    noticeList.addAll(response.getResult().getMsgList());


                    firstContent = response.getResult().getMsgList().get(0).getMsg();


                    totalPage = ((response.getResult().getCount()) / 10) + 1;



                    if(firstContent != null){
                        text_first_ment2.setText(firstContent);
                    }else{
                        text_first_ment2.setText("아직 올라온 글이 없어요");
                    }


                    firstHeartNum = response.getResult().getMsgList().get(0).getHeartNum();

                    if(firstHeartNum != null){

                            text_heart_number.setText(firstHeartNum);
                        LL_weatherHeart.setVisibility(View.VISIBLE);

                    }else{
                        LL_weatherHeart.setVisibility(View.INVISIBLE);
                    }

                    mNoticeBoardAdapter.notifyDataSetChanged(); //view차원에서 업데이트

                    break;

                case 1226:

                    Log.d("확인", "조회할 게시글이 없습니다.");
                    text_first_ment2.setText("아직 올라온 글이 없어요");
                    LL_weatherHeart.setVisibility(View.INVISIBLE);

                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateWeatherSuccess(NowResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1203:
                    Log.d("확인", "현재날씨 성공");
                    textNow.setText(response.getResult().getOndo());
                    text_nowOndo.setText(response.getResult().getOndo());

                    Log.d("확인", response.getResult().getSky() + "--" + response.getResult().getRain());

                    if (response.getResult().getSky().equals("1") && response.getResult().getRain().equals("0")) {
                        text_ment1.setText("맑아요");
                        text_ment2.setText("맑아요");
                        img_weather.setImageResource(R.drawable.one1);
                        img_quater.setImageResource(R.drawable.day_sunnycut);
                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("0")) {

                        text_ment1.setText("구름이 많아요");
                        text_ment2.setText("구름이 많아요");
                        img_weather.setImageResource(R.drawable.one3);
                        img_quater.setImageResource(R.drawable.second2);

                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("1")) {
                        text_ment1.setText("구름이 많고 비가 와요");
                        text_ment2.setText("구름이 많고 비가 와요");
                        img_weather.setImageResource(R.drawable.one7);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("2")) {
                        text_ment1.setText("구름이 많고\n비나 눈이 와요");
                        text_ment2.setText("구름이 많고\n비나 눈이 와요");

                        img_weather.setImageResource(R.drawable.one7);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("3")) {
                        text_ment1.setText("구름이 많고\n눈이 와요");
                        text_ment2.setText("구름이 많고\n눈이 와요");
                        img_weather.setImageResource(R.drawable.one8);
                        img_quater.setImageResource(R.drawable.second5);
                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("4")) {
                        text_ment1.setText("구름이 많고\n소나기가 와요");
                        text_ment2.setText("구름이 많고\n소나기가 와요");
                        img_weather.setImageResource(R.drawable.one10);
                        img_quater.setImageResource(R.drawable.second10);
                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("5")) {
                        text_ment1.setText("구름이 많고\n빗방울이 떨어져요");
                        text_ment2.setText("구름이 많고\n빗방울이 떨어져요");
                        img_weather.setImageResource(R.drawable.one7);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("6")) {
                        text_ment1.setText("구름이 많고\n빗방울이나 눈날림이 있어요");
                        text_ment2.setText("구름이 많고\n빗방울이나 눈날림이 있어요");
                        img_weather.setImageResource(R.drawable.one7);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("3") && response.getResult().getRain().equals("7")) {
                        text_ment1.setText("구름이 많고\n눈날림이 있어요");
                        text_ment2.setText("구름이 많고\n눈날림이 있어요");
                        img_weather.setImageResource(R.drawable.one8);
                        img_quater.setImageResource(R.drawable.second5);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("0")) {
                        text_ment1.setText("흐려요");
                        text_ment2.setText("흐려요");
                        img_weather.setImageResource(R.drawable.one4);
                        img_quater.setImageResource(R.drawable.second11);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("1")) {
                        text_ment1.setText("흐리고\n비가 와요");
                        text_ment2.setText("흐리고\n비가 와요");
                        img_weather.setImageResource(R.drawable.one5);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("2")) {
                        text_ment1.setText("흐리고\n비나 눈이 와요");
                        text_ment2.setText("흐리고\n비나 눈이 와요");
                        img_weather.setImageResource(R.drawable.one5);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("3")) {
                        text_ment1.setText("흐리고\n눈이 와요");
                        text_ment2.setText("흐리고\n눈이 와요");

                        img_weather.setImageResource(R.drawable.one6);
                        img_quater.setImageResource(R.drawable.second5);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("4")) {
                        text_ment1.setText("흐리고\n소나기가 와요");
                        text_ment2.setText("흐리고\n소나기가 와요");
                        img_weather.setImageResource(R.drawable.one5);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("5")) {
                        text_ment1.setText("흐리고\n빗방울이 떨어져요");
                        text_ment2.setText("흐리고\n빗방울이 떨어져요");
                        img_weather.setImageResource(R.drawable.one5);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("6")) {
                        text_ment1.setText("흐리고\n빗방울이나 눈날림이 있어요");
                        text_ment2.setText("흐리고\n빗방울이나 눈날림이 있어요");
                        img_weather.setImageResource(R.drawable.one5);
                        img_quater.setImageResource(R.drawable.second4);
                    } else if (response.getResult().getSky().equals("4") && response.getResult().getRain().equals("7")) {
                        text_ment1.setText("흐리고\n눈날림이 있어요");
                        text_ment2.setText("흐리고\n눈날림이 있어요");
                        img_weather.setImageResource(R.drawable.one6);
                        img_quater.setImageResource(R.drawable.second5);
                    }

                    textGu.setText(response.getResult().getGu());
                    textGu2.setText(response.getResult().getGu());
                    textGu3.setText(response.getResult().getGu());
                    textGu4.setText(response.getResult().getGu());

                    gu = response.getResult().getGu();

                    break;
            }
        } else {
            Log.d("확인", response.getMessage());
            showCustomToast("네트워크 연결이 원활하지 않습니다.");


        }
    }

    @Override
    public void validateFailure(String message) {

    }
}