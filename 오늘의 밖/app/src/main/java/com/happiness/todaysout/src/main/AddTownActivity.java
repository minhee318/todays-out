package com.happiness.todaysout.src.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.login.Interface.OnItemClick;
import com.happiness.todaysout.src.main.Adapter.MainSearchAdapter;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.NICKNAME;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class AddTownActivity extends BaseActivity implements View.OnClickListener,OnItemClick {

    ImageView btn_backadd;
    LinearLayout text_add_town; //검색하는 순간 사라져야 함
    ImageView img_add_town; //검색하는 순간 사라져야 함
    EditText edittext_addtown;
    LinearLayout LL_clicksearch;
    String inputGu;
    TextView text_inputresult;
    TextView text_inputresult2;
    LinearLayout LL_dongInput; //동 입력시
    TextView text_guInpput; // 구 입력시
    MainSearchAdapter mMainSearchAdapter;
    RecyclerView rc_search;
    LinearLayout LL_result;
    ArrayList<String> mainGuList = new ArrayList<>(); //구정보를 담을 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_town);

        btn_backadd = findViewById(R.id.btn_backadd);
        btn_backadd.setOnClickListener(this);
        text_add_town = findViewById(R.id.text_add_town);
        img_add_town = findViewById(R.id.img_add_town);
        edittext_addtown = findViewById(R.id.edittext_addtown);
        LL_clicksearch = findViewById(R.id.LL_clicksearch);
        text_inputresult = findViewById(R.id.text_inputresult);
        text_inputresult2 = findViewById(R.id.text_inputresult2);
        LL_dongInput = findViewById(R.id.LL_dongInput);
        text_guInpput = findViewById(R.id.text_guInpput);
        rc_search = findViewById(R.id.rc_search);
        LL_result = findViewById(R.id.LL_result);

        mainGuList.add("강남구");
        mainGuList.add("강동구");
        mainGuList.add("강북구");
        mainGuList.add("강서구");
        mainGuList.add("관악구");
        mainGuList.add("광진구");
        mainGuList.add("구로구");
        mainGuList.add("금천구");
        mainGuList.add("노원구");
        mainGuList.add("도봉구");
        mainGuList.add("동대문구");
        mainGuList.add("동작구");
        mainGuList.add("마포구");
        mainGuList.add("서대문구");
        mainGuList.add("서초구");
        mainGuList.add("성동구");
        mainGuList.add("성북구");
        mainGuList.add("송파구");
        mainGuList.add("양천구");
        mainGuList.add("영등포구");
        mainGuList.add("용산구");
        mainGuList.add("은평구");
        mainGuList.add("종로구");
        mainGuList.add("중구");
        mainGuList.add("중랑구");



        edittext_addtown.addTextChangedListener(new TextWatcher() {
            // 입력되는 텍스트에 변화가 있을 때
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMainSearchAdapter.getFilter().filter(s);


                if (edittext_addtown.getText().length() == 0) {
                    text_add_town.setVisibility(View.VISIBLE);
                    img_add_town.setVisibility(View.VISIBLE);
                    LL_clicksearch.setVisibility(View.INVISIBLE);
                    rc_search.setVisibility(View.GONE);
                    LL_result.setVisibility(View.GONE);

                } else {
                    text_add_town.setVisibility(View.INVISIBLE);
                    img_add_town.setVisibility(View.INVISIBLE);
                    LL_clicksearch.setVisibility(View.VISIBLE);
                    rc_search.setVisibility(View.VISIBLE);
                    LL_result.setVisibility(View.VISIBLE);



                }
            }

            // 입력이 끝났을 때
            @Override
            public void afterTextChanged(Editable editable) {
                inputGu = edittext_addtown.getText().toString().trim();

                text_inputresult.setText(inputGu);
                text_inputresult2.setText(inputGu);

            }

            // 입력하기 전에
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });


        mMainSearchAdapter = new MainSearchAdapter(AddTownActivity.this, mainGuList,this);
        rc_search.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rc_search.setAdapter(mMainSearchAdapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_backadd:
                finish();
                break;
        }
    }


    @Override
    public void onClick(String state) {
        final Dialog dlg = new Dialog(this);

        dlg.setCancelable(false);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.dialog_gu);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        dlg.show();
        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Log.d("확인", "다이얼로그 닫힘!!");
            }
        });

        final TextView btn_yes = (TextView) dlg.findViewById(R.id.btn_yes);
        final ImageView btn_no = (ImageView) dlg.findViewById(R.id.btn_no);
        final TextView text_guName = (TextView) dlg.findViewById(R.id.text_guName);
        final TextView text_guName2 = (TextView) dlg.findViewById(R.id.text_guName2);
        final TextView text_nickname = (TextView) dlg.findViewById(R.id.text_nickname);


        text_guName.setText(state);
        text_guName2.setText(state);
        text_nickname.setText(sSharedPreferences.getString(NICKNAME, null));


        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("직접선택구",state);
                setResult(RESULT_OK, intent);
                finish();
                dlg.dismiss();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });

    }
}