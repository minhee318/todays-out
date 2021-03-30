package com.softsquared.todaysout.src.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.BaseActivity;
import com.softsquared.todaysout.src.login.Adapter.SearchAdapter;
import com.softsquared.todaysout.src.login.Interface.OnItemClick;

import java.util.ArrayList;

import static com.softsquared.todaysout.src.ApplicationClass.NICKNAME;
import static com.softsquared.todaysout.src.ApplicationClass.sSharedPreferences;

public class SearchActivity extends BaseActivity implements View.OnClickListener, OnItemClick {
    ImageView btn_cancel;
    EditText editText_search;
    String gu;
    ArrayList<String> guList = new ArrayList<>(); //구정보를 담을 리스트
    RecyclerView rc_result;
    LinearLayout LL_searchment; //한글자라도 써지면 사라지게 하자
    ImageView img_search; //한글자라도 써지면 사라지게 하자
    SearchAdapter mSearchAdapter;
    String nickName3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Intent intent = getIntent();
        nickName3 = intent.getStringExtra("닉네임");

        guList.add("강남구");
        guList.add("강동구");
        guList.add("강북구");
        guList.add("강서구");
        guList.add("관악구");
        guList.add("광진구");
        guList.add("구로구");
        guList.add("금천구");
        guList.add("노원구");
        guList.add("도봉구");
        guList.add("동대문구");
        guList.add("동작구");
        guList.add("마포구");
        guList.add("서대문구");
        guList.add("서초구");
        guList.add("성동구");
        guList.add("성북구");
        guList.add("송파구");
        guList.add("양천구");
        guList.add("영등포구");
        guList.add("용산구");
        guList.add("은평구");
        guList.add("종로구");
        guList.add("중구");
        guList.add("중랑구");



        img_search = findViewById(R.id.img_search);
        LL_searchment = findViewById(R.id.LL_searchment);
        rc_result = findViewById(R.id.rc_result);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        editText_search = findViewById(R.id.editText_search);

        mSearchAdapter = new SearchAdapter(SearchActivity.this, guList,this);
        rc_result.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rc_result.setAdapter(mSearchAdapter);



        editText_search.addTextChangedListener(new TextWatcher() {
            // 입력되는 텍스트에 변화가 있을 때
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSearchAdapter.getFilter().filter(s);

                if (editText_search.getText().length() == 0) {
                    img_search.setVisibility(View.VISIBLE);
                    LL_searchment.setVisibility(View.VISIBLE);
                    rc_result.setVisibility(View.GONE);

                } else {
                    img_search.setVisibility(View.GONE);
                    LL_searchment.setVisibility(View.GONE);
                    rc_result.setVisibility(View.VISIBLE);


                }
            }

            // 입력이 끝났을 때
            @Override
            public void afterTextChanged(Editable editable) {
                gu = editText_search.getText().toString();

            }

            // 입력하기 전에
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
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