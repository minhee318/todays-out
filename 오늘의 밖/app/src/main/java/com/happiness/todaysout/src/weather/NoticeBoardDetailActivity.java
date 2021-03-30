package com.happiness.todaysout.src.weather;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.ApplicationClass;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.weather.Adapter.CommentAdapter;
import com.happiness.todaysout.src.weather.interfaces.WeatherActivityView;
import com.happiness.todaysout.src.weather.models.BoardPostResponse;
import com.happiness.todaysout.src.weather.models.BoardResponse;
import com.happiness.todaysout.src.weather.models.CommentInfo;
import com.happiness.todaysout.src.weather.models.CommentResponse;
import com.happiness.todaysout.src.weather.models.DetailResponse;
import com.happiness.todaysout.src.weather.models.DongInfo;
import com.happiness.todaysout.src.weather.models.DongResponse;
import com.happiness.todaysout.src.weather.models.DustResponse;
import com.happiness.todaysout.src.weather.models.HeartPostResponse;
import com.happiness.todaysout.src.weather.models.NowResponse;
import com.happiness.todaysout.src.weather.models.PatchResponse;
import com.happiness.todaysout.src.weather.models.PostCommentInfo;
import com.happiness.todaysout.src.weather.models.RegisterInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;
import com.happiness.todaysout.src.weather.models.TodayResponse;
import com.happiness.todaysout.src.weather.models.UpDownResponse;
import com.happiness.todaysout.src.weather.models.WeekResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.FIRST_DONG;
import static com.happiness.todaysout.src.ApplicationClass.IS_FIRST;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_DONG;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class NoticeBoardDetailActivity extends BaseActivity implements View.OnClickListener, WeatherActivityView {

    LinearLayout LL_heart;
    LinearLayoutManager layoutManager;
    ImageView fullheart;
    ImageView emptyheart;
    ImageView imgDot;
    CircleImageView imgProfile;
    ImageView btn_backDetail;
    TextView textNickName;
    TextView textDong;
    TextView textContent;
    TextView textTime;
    TextView textCommentNum;
    TextView textHeartNum;
    RecyclerView rc_comment;
    LinearLayout LL_dongsearch;
    LinearLayout LL_emptydong;
    TextView textMyDong;
    Long userIdx;
    String content;
    Long addressIdx;
    TextView modify;
    TextView report;
    ImageView img_post;
    String comment;
    EditText edittext_comment;
    String myDong;


    String dong;
    Long msgIdx;
    String gu;

    CommentAdapter mCommentAdapter;
    ArrayList<DongInfo> dongList = new ArrayList<>();
    ArrayList<CommentInfo> commentList = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>();
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void onResume() {
        super.onResume();


        comment();
        tryGetCommentInfo(msgIdx, 0);

        tryGetHeartInfo(msgIdx);
        tryGetDetailInfo(msgIdx);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board_detail);

        img_post = findViewById(R.id.img_post);
        img_post.setOnClickListener(this);

        edittext_comment = findViewById(R.id.edittext_comment);

        LL_heart = findViewById(R.id.LL_heart);
        textTime = findViewById(R.id.textTime);
        textHeartNum = findViewById(R.id.textHeartNum);
        textContent = findViewById(R.id.textContent);
        textCommentNum = findViewById(R.id.textCommentNum);
        fullheart = findViewById(R.id.fullheart);
        emptyheart = findViewById(R.id.emptyheart);
        imgDot = findViewById(R.id.imgDot);
        imgProfile = findViewById(R.id.imgProfile);
        btn_backDetail = findViewById(R.id.btn_backDetail);
        textNickName = findViewById(R.id.textNickName);
        textDong = findViewById(R.id.textDong);
        rc_comment = findViewById(R.id.rc_comment);
        LL_dongsearch = findViewById(R.id.LL_dongsearch);
        LL_emptydong = findViewById(R.id.LL_emptydong);
        textMyDong = findViewById(R.id.textMyDong);

        LL_dongsearch.setOnClickListener(this);
        LL_emptydong.setOnClickListener(this);
        LL_heart.setOnClickListener(this);
        btn_backDetail.setOnClickListener(this);
        imgDot.setOnClickListener(this);


        LL_dongsearch = findViewById(R.id.LL_dongsearch);
        LL_emptydong = findViewById(R.id.LL_emptydong);


        Intent notice = getIntent();
        msgIdx = notice.getLongExtra("msgIdx", -1);
        addressIdx = notice.getLongExtra("addressIdx",-1);
        Log.d("확인", "게시판 상세화면"+msgIdx+addressIdx);



        if(sSharedPreferences.getString(SECOND_DONG, null) != null){
            Log.d("확인","두번째 동으로 등록된 곳: "+sSharedPreferences.getString(SECOND_DONG, null));
        }else if(sSharedPreferences.getString(FIRST_DONG, null) != null){
            Log.d("확인","첫번째 동으로 등록된 곳: "+sSharedPreferences.getString(FIRST_DONG, null));
        }





        if (sSharedPreferences.getBoolean(String.valueOf(IS_FIRST), false) == true) {
            LL_emptydong.setVisibility(View.INVISIBLE);
            LL_dongsearch.setVisibility(View.VISIBLE);

            if(addressIdx == sSharedPreferences.getLong(FIRST_ADDRESSIDX, -1)){
                textMyDong.setText(sSharedPreferences.getString(FIRST_DONG, null));
                myDong = sSharedPreferences.getString(FIRST_DONG, null);
            }else if(addressIdx == sSharedPreferences.getLong(SECOND_ADDRESSIDX, -1)){
                textMyDong.setText(sSharedPreferences.getString(SECOND_DONG, null));
                myDong = sSharedPreferences.getString(SECOND_DONG, null);
            }

        }

        edittext_comment.addTextChangedListener(new TextWatcher() {
            // 입력되는 텍스트에 변화가 있을 때
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            // 입력이 끝났을 때
            @Override
            public void afterTextChanged(Editable editable) {
                comment = edittext_comment.getText().toString();

            }

            // 입력하기 전에
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        comment();

        tryGetDongInfo(addressIdx);
        tryGetDetailInfo(msgIdx);

        tryGetCommentInfo(msgIdx, 0);

        tryGetHeartInfo(msgIdx);

    }

    private void tryPostReporttInfo(Long msgIdx) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.postReport(msgIdx);
    }

    private void tryPostCommentInfo(Long msgIdx) {
        Log.d("확인","댓글 등록성공1");

        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        Log.d("확인","낭하"+sSharedPreferences.getLong("USER_IDX", -1)+gu+myDong+comment);
        weatherService.postComment(msgIdx,new PostCommentInfo(sSharedPreferences.getLong("USER_IDX", -1),gu,myDong,comment));
    }


    private void tryGetDetailInfo(Long msgIdx) {
        final WeatherService weatherService = new WeatherService(this);
        weatherService.getDetail(msgIdx);
    }

    private void tryPostHeartInfo(Long msgIdx) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.postHeart(msgIdx);
    }

    private void tryGetHeartInfo(Long msgIdx) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.getHeart(msgIdx);
    }


    private void tryGetDongInfo(Long addressIdx) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.getDong(addressIdx);
    }

    private void tryPostRegisterInfo(Long addressIdx,String dong) {
        showProgressDialog();
        final WeatherService weatherService = new WeatherService(this);
        weatherService.postRegister(addressIdx, new RegisterInfo(dong));
    }


    private void tryGetCommentInfo(Long msgIdx, int page) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.getComment(msgIdx, page);
    }

    private void comment() {
        layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_comment.setLayoutManager(layoutManager);

        mCommentAdapter = new CommentAdapter(this, commentList,msgIdx);
        rc_comment.setAdapter(mCommentAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LL_heart:
                if (fullheart.getVisibility() == View.VISIBLE) { //하트누른상태에서 꺼짐
                    fullheart.setVisibility(View.INVISIBLE);
                    emptyheart.setVisibility(View.VISIBLE);
                    tryPostHeartInfo(msgIdx);
                    // tryGetHeartInfo(msgIdx);
                } else if (fullheart.getVisibility() == View.INVISIBLE) { //하트꺼진상태에서 켜짐
                    fullheart.setVisibility(View.VISIBLE);
                    emptyheart.setVisibility(View.INVISIBLE);
                    tryPostHeartInfo(msgIdx);
                    //tryGetHeartInfo(msgIdx);
                }
                break;
            case R.id.imgDot:
                final BottomSheetDialog editDialog = new BottomSheetDialog(
                        NoticeBoardDetailActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.editdetail_dialog,
                                (LinearLayout) findViewById(R.id.bottomSheetContainer)
                        );


                modify = (TextView) bottomSheetView.findViewById(R.id.btn_0);

                modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent modify = new Intent(NoticeBoardDetailActivity.this, WriteActivity.class);
                        modify.putExtra("modify", "modify");
                        modify.putExtra("content", content);
                        modify.putExtra("msgIdx", msgIdx);
                        modify.putExtra("gu", gu);
                        modify.putExtra("addressIdx",addressIdx);
                        startActivity(modify);

                        editDialog.cancel();
                    }
                });

                report = (TextView) bottomSheetView.findViewById(R.id.btn_1);

                report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tryPostReporttInfo(msgIdx);
                        editDialog.cancel();
                    }
                });

                Long userIdx2 = sSharedPreferences.getLong("USER_IDX", -1);

                if (userIdx != userIdx2) {
                    modify.setVisibility(View.GONE);
                }

                editDialog.setContentView(bottomSheetView);
                editDialog.show();
                break;
            case R.id.btn_backDetail:
                finish();
                break;

            case R.id.img_post:
                tryPostCommentInfo(msgIdx);

                break;
            case R.id.LL_dongsearch:
//                LL_dongsearch.setVisibility(View.INVISIBLE);
//                LL_emptydong.setVisibility(View.VISIBLE);
                break;
            case R.id.LL_emptydong:
//                LL_emptydong.setVisibility(View.INVISIBLE);
//                LL_dongsearch.setVisibility(View.VISIBLE);
//
                    final Dialog dlg2 = new Dialog(this);

                    dlg2.setCancelable(false);
                    dlg2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dlg2.setContentView(R.layout.dialog_dong);
                    dlg2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                    dlg2.show();
                    dlg2.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

                        }
                    });


                    final ImageView btn_no = (ImageView) dlg2.findViewById(R.id.btn_no);
                    final TextView btn_yes = (TextView) dlg2.findViewById(R.id.btn_yes);
                    final Spinner spinner = dlg2.findViewById(R.id.spinner);
                final TextView text_guName = dlg2.findViewById(R.id.text_guName);

                    text_guName.setText(gu+"의");

                    items.add("동을 선택해주세요");


                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

                    adapter.setDropDownViewResource(
                            android.R.layout.simple_spinner_dropdown_item);

                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            dong = items.get(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                    btn_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dlg2.dismiss();
                        }
                    });

                    btn_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (dong.length() != 0) {
                                if (dong.equals("동을 선택해주세요")) {
                                    showCustomToast("동을 선택해주세요");
                                } else {

                                    textDong.setText(dong);

                                    tryPostRegisterInfo(addressIdx,dong);

                                    SharedPreferences.Editor editor = sSharedPreferences.edit();

                                    if (addressIdx == sSharedPreferences.getLong(FIRST_ADDRESSIDX, -1)) {
                                        Log.d("확인","첫번째 동");
                                        editor.putString(FIRST_DONG, dong);
                                    } else if (addressIdx == sSharedPreferences.getLong(SECOND_ADDRESSIDX, -1)) {
                                        Log.d("확인","두번째 동");
                                        editor.putString(SECOND_DONG, dong);
                                    }

                                    editor.putBoolean(String.valueOf(ApplicationClass.IS_FIRST), true);

                                    editor.apply();

                                    dlg2.dismiss();

                                }

                            }

                            dlg2.dismiss();
                        }
                    });

                break;

        }
    }

    @Override
    public void validateUpDownSuccess(UpDownResponse response) {

    }

    @Override
    public void validateDustSuccess(DustResponse response) {

    }

    @Override
    public void validateTodaySuccess(TodayResponse response) {

    }

    @Override
    public void validateDetailSuccess(DetailResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1217:
                    Log.d("확인", "게시글조회 성공");
                    if (response.getResult().getPicture() != null) {
                        Glide
                                .with(this)
                                .load(response.getResult().getPicture())
                                .into(imgProfile);
                    }

                    textNickName.setText(response.getResult().getNickName());

                    textDong.setText(response.getResult().getDong());

                    textContent.setText(response.getResult().getMsg());

                    content = response.getResult().getMsg();

                    textHeartNum.setText(response.getResult().getHeartNum());

                    textCommentNum.setText(response.getResult().getCommentNum());

                    userIdx = response.getResult().getUserIdx();

                    gu = response.getResult().getGu();

                    try {

                        Date date = sdfNow.parse(response.getResult().getDate());

                        if (calculateTime(date).equals("hi")) {
                            String day = response.getResult().getDate().substring(0, 10);
                            textTime.setText(day);
                        } else {
                            textTime.setText(calculateTime(date));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        } else {
            Log.d("확인", response.getMessage());
            showCustomToast("네트워크 연결이 원활하지 않습니다.");


        }
    }

    @Override
    public void validateCommentSuccess(CommentResponse response) {
        hideProgressDialog();
        Log.d("확인", "게시글댓글 성공" + response.getCode());
        if (response.getIsSuccess()) {

            switch (response.getCode()) {
                case 1218:
                    Log.d("확인", "게시글댓글 성공");
                    commentList.clear(); //먼저 eventList에 있는 것들을 다 지운다.

                    commentList.addAll(response.getResult());

                    mCommentAdapter.notifyDataSetChanged(); //view차원에서 업데이트
                    break;

                case 1227:
                    Log.d("확인", response.getMessage());

                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateHeartSuccess(HeartPostResponse response) {

        Log.d("확인", response.getMessage());
        tryGetDetailInfo(msgIdx);


    }

    @Override
    public void validateHeartGetSuccess(HeartPostResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            Log.d("확인", "하트정보 받아오기");
            switch (response.getCode()) {
                case 1230:

                    if (response.getResult().getStatus().equals("Y")) {
                        fullheart.setVisibility(View.VISIBLE);
                        emptyheart.setVisibility(View.INVISIBLE);
                    } else {
                        fullheart.setVisibility(View.INVISIBLE);
                        emptyheart.setVisibility(View.VISIBLE);
                    }

                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateContentSuccess(BoardPostResponse response) {

    }

    @Override
    public void validateReportSuccess(ReportResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {

            switch (response.getCode()) {
                case 1228:
                    showCustomToast(response.getMessage());
                    break;
            }
        } else {
            switch (response.getCode()) {
                case 3208:
                    showCustomToast(response.getMessage());
                    break;
                case 3207:
                    showCustomToast(response.getMessage());
                    break;
                case 3010:
                    showCustomToast(response.getMessage());
                    break;
                case 3205:
                    showCustomToast(response.getMessage());
                    break;

            }


        }
    }

    @Override
    public void validatePostCommentSuccess(ReportResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            Log.d("확인","댓글 등록성공"+response.getCode());
            switch (response.getCode()) {
                case 1300:
                    showCustomToast(response.getMessage());
                    tryGetCommentInfo(msgIdx, 0);
                    edittext_comment.setText("");
                    break;
            }
        } else {
            switch (response.getCode()) {
                case 2300:
                    showCustomToast(response.getMessage());
                    break;
                default:
                    showCustomToast("네트워크 연결이 원활하지 않습니다.");
                    Log.d("확인",response.getMessage());
            }




        }
    }

    @Override
    public void validatePatchSuccess(PatchResponse response) {

    }

    @Override
    public void validateDongSuccess(DongResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1210:
                    dongList.clear(); //먼저 eventList에 있는 것들을 다 지운다.

                    dongList.addAll(response.getResult());


                    for(int i=0;i<dongList.size();i++){
                        items.add(dongList.get(i).getDong());
                    }

                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }
    }

    @Override
    public void validateWeekSuccess(WeekResponse response) {

    }

    @Override
    public void validateHeartSuccess(BoardResponse response) {

    }

    @Override
    public void validateRegisterSuccess(ReportResponse response) {

    }

    @Override
    public void validateBoardSuccess(BoardResponse response) {

    }

    @Override
    public void validateWeatherSuccess(NowResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }

    private static class TIME_MAXIMUM {
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }

    public String calculateTime(Date date) {

        long curTime = System.currentTimeMillis();
        long regTime = date.getTime();
        long diffTime = (curTime - regTime) / 1000;

        String msg = null;

        if (diffTime < TIME_MAXIMUM.SEC) {
            // sec
            msg = diffTime + "초전";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            // min
            System.out.println(diffTime);

            msg = diffTime + "분전";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            // hour
            msg = (diffTime) + "시간전";
        }
//        else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY)
//        {
//            // day
//            msg = (diffTime ) + "일전";
//        }
//        else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH)
//        {
//            // day
//            msg = (diffTime ) + "달전";
//        }
        else {
//            msg = (diffTime) + "년전";
            msg = "hi";

        }

        return msg;
    }

}
