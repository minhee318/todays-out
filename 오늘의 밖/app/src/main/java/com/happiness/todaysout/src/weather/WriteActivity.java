package com.happiness.todaysout.src.weather;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.ApplicationClass;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.weather.interfaces.WeatherActivityView;
import com.happiness.todaysout.src.weather.models.BoardPostResponse;
import com.happiness.todaysout.src.weather.models.BoardResponse;
import com.happiness.todaysout.src.weather.models.CommentResponse;
import com.happiness.todaysout.src.weather.models.ContentInfo;
import com.happiness.todaysout.src.weather.models.ContentPatchInfo;
import com.happiness.todaysout.src.weather.models.DetailResponse;
import com.happiness.todaysout.src.weather.models.DongInfo;
import com.happiness.todaysout.src.weather.models.DongResponse;
import com.happiness.todaysout.src.weather.models.DustResponse;
import com.happiness.todaysout.src.weather.models.HeartPostResponse;
import com.happiness.todaysout.src.weather.models.NowResponse;
import com.happiness.todaysout.src.weather.models.PatchResponse;
import com.happiness.todaysout.src.weather.models.RegisterInfo;
import com.happiness.todaysout.src.weather.models.ReportResponse;
import com.happiness.todaysout.src.weather.models.TodayResponse;
import com.happiness.todaysout.src.weather.models.UpDownResponse;
import com.happiness.todaysout.src.weather.models.WeekResponse;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.FIRST_DONG;
import static com.happiness.todaysout.src.ApplicationClass.IS_FIRST;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_DONG;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class WriteActivity extends BaseActivity implements View.OnClickListener, WeatherActivityView {

    String gu;
    TextView btn_cancel;
    ImageView btn_gray;
    ImageView btn_blue;
    TextView textGu;
    TextView textDong;
    EditText editText_content;
    String msg;
    Long addressIdx;
    String modify;
    String content;
    Long msgIdx;
    String dong;
    ArrayList<DongInfo> dongList = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        if(sSharedPreferences.getString(SECOND_DONG, null) != null){
            Log.d("확인","두번째 동으로 등록된 곳: "+sSharedPreferences.getString(SECOND_DONG, null));
        }else if(sSharedPreferences.getString(FIRST_DONG, null) != null){
            Log.d("확인","첫번째 동으로 등록된 곳: "+sSharedPreferences.getString(FIRST_DONG, null));
        }


        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        btn_gray = findViewById(R.id.btn_gray);
        btn_blue = findViewById(R.id.btn_blue);
        btn_blue.setOnClickListener(this);
        textGu = findViewById(R.id.textGu);
        textDong = findViewById(R.id.textDong);
        editText_content = findViewById(R.id.editText_content);

        Intent getIntent = getIntent();
        modify = getIntent.getStringExtra("modify");

        if(modify != null) {
            msg = getIntent.getStringExtra("content");
            msgIdx = getIntent.getLongExtra("msgIdx",-1);
            gu = getIntent.getStringExtra("gu");
            textGu.setText(gu);

            addressIdx = getIntent.getLongExtra("addressIdx",-1);
            editText_content.setText(msg);

            if (msg.length() == 0) {
                btn_gray.setVisibility(View.VISIBLE);
                btn_blue.setVisibility(View.INVISIBLE);


            } else {

                btn_gray.setVisibility(View.INVISIBLE);
                btn_blue.setVisibility(View.VISIBLE);


            }


            Log.d("확인","수정하기에서 구정보"+gu);

        }else{
            Intent write = getIntent();
            gu = write.getStringExtra("구정보");
            addressIdx = write.getLongExtra("addressIdx",-1);
            textGu.setText(gu);

            Log.d("확인","글쓰기에서 구정보"+gu);
        }

        tryGetDongInfo(addressIdx);




            Log.d("확인",sSharedPreferences.getBoolean(String.valueOf(IS_FIRST), false)+"");
        if (sSharedPreferences.getBoolean(String.valueOf(IS_FIRST), false) == true) {

            if(addressIdx == sSharedPreferences.getLong(FIRST_ADDRESSIDX, -1)){
                textDong.setText(sSharedPreferences.getString(FIRST_DONG, null));
            }else if(addressIdx == sSharedPreferences.getLong(SECOND_ADDRESSIDX, -1)){
                textDong.setText(sSharedPreferences.getString(SECOND_DONG, null));
            }

        }else{
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
                        Log.d("확인",dong);



                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   showCustomToast("동을 선택해야 글쓰기가 가능해요!");
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
                }
            });
        }







        editText_content.addTextChangedListener(new TextWatcher() {
            // 입력되는 텍스트에 변화가 있을 때
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText_content.getText().length() == 0) {
                    btn_gray.setVisibility(View.VISIBLE);
                    btn_blue.setVisibility(View.INVISIBLE);


                } else {

                    btn_gray.setVisibility(View.INVISIBLE);
                    btn_blue.setVisibility(View.VISIBLE);


                }
            }

            // 입력이 끝났을 때
            @Override
            public void afterTextChanged(Editable editable) {
                msg = editText_content.getText().toString();

            }

            // 입력하기 전에
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    private void tryGetDongInfo(Long addressIdx) {
        showProgressDialog();

        final WeatherService weatherService = new WeatherService(this);
        weatherService.getDong(addressIdx);
    }

    private void tryPostContentInfo(Long addressIdx, String msg) {
        showProgressDialog();
        final WeatherService weatherService = new WeatherService(this);
        weatherService.postContent(addressIdx, new ContentInfo(msg,"WEATHER"));
    }

    private void tryPostRegisterInfo(Long addressIdx,String dong) {
        showProgressDialog();
        final WeatherService weatherService = new WeatherService(this);
        weatherService.postRegister(addressIdx, new RegisterInfo(dong));
    }

    private void tryPatchContentInfo(Long msgIdx2, String msg1) {
        showProgressDialog();
        final WeatherService weatherService = new WeatherService(this);
        weatherService.patchContent(msgIdx2,new ContentPatchInfo(msg1));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                finish();
                break;

            case R.id.btn_blue:
                if(modify != null) {
                    Log.d("확인",msgIdx+msg+"수정하기클릭");
                    tryPatchContentInfo(msgIdx,msg);
                }else{
                    Log.d("확인",addressIdx+msg+"글쓰기클릭");
                    tryPostContentInfo(addressIdx,msg);
                }

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
        hideProgressDialog();

        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1212:
                    showCustomToast(response.getMessage());
                    finish();
                    break;
            }
        } else {
            Log.d("확인",response.getMessage());
            showCustomToast("네트워크 연결이 원활하지 않습니다.");
        }



    }

    @Override
    public void validateReportSuccess(ReportResponse response) {

    }

    @Override
    public void validatePostCommentSuccess(ReportResponse response) {

    }

    @Override
    public void validatePatchSuccess(PatchResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {

            switch (response.getCode()) {
                case 1213:
                    showCustomToast(response.getMessage());
                    finish();
                    break;
            }
        } else {
            switch (response.getCode()) {
                case 3010:
                    showCustomToast(response.getMessage());
                    break;
                default:
                    Log.d("확인", response.getMessage());
                    break;

            }
        }

    }

    @Override
    public void validateDongSuccess(DongResponse response) {
        hideProgressDialog();

        Log.d("확인","동 조회 성공"+response.getCode());
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
            hideProgressDialog();
        Log.d("확인","동 등록 성공!");
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
}