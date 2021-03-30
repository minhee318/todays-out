package com.happiness.todaysout.src.main;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.main.Adapter.EditAdapter;
import com.happiness.todaysout.src.main.Adapter.EditClickAdapter;
import com.happiness.todaysout.src.main.interfaces.MainActivityView;
import com.happiness.todaysout.src.main.models.AddressGetResponse;
import com.happiness.todaysout.src.main.models.AddressInfo;
import com.happiness.todaysout.src.main.models.DisasterResponse;
import com.happiness.todaysout.src.main.models.HomeWeatherResponse;
import com.happiness.todaysout.src.main.models.MainPostInfo;
import com.happiness.todaysout.src.main.models.MainPostResponse;
import com.happiness.todaysout.src.main.models.MainResponse;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.NICKNAME;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class EditTownActivity extends BaseActivity implements View.OnClickListener, MainActivityView {

    ImageView btn_backedit;
    TextView btn_edit;
    TextView btn_save;
    LinearLayout btn_add_town;
    RecyclerView rc_edit;
    RecyclerView rc_edit2;
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManager2;
    ItemTouchHelper helper;
    TextView text;
    String nickname;
    Long addressIdx;
    ImageView guide;
    LinearLayout btn_nowlocation2;
    TextView text_afterchangegu;
    TextView nickname2;


    ArrayList<AddressInfo> guList = new ArrayList<>();

    private final int REQUEST_TEST2 = 2;

    EditAdapter mEditAdapter;
    EditClickAdapter mEditClickAdapter;


    boolean oneItem = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_town);

        guide = findViewById(R.id.guide);


        nickname2 = findViewById(R.id.nickname);
        nickname2.setText(sSharedPreferences.getString(NICKNAME,null));

        text_afterchangegu = findViewById(R.id.text_afterchangegu);

        nickname = sSharedPreferences.getString(NICKNAME, null);

        btn_nowlocation2 = findViewById(R.id.btn_nowlocation2);


        btn_backedit = findViewById(R.id.btn_backedit);
        btn_backedit.setOnClickListener(this);
        btn_edit = findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(this);
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_add_town = findViewById(R.id.btn_add_town);
        btn_add_town.setOnClickListener(this);
        rc_edit = findViewById(R.id.rc_edit);
        rc_edit2 = findViewById(R.id.rc_edit2);
        text = findViewById(R.id.text);

        tryGetAddress();
        guInfo();
        guInfo2();





    }

    private void guInfo() {
        layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_edit.setLayoutManager(layoutManager);

        mEditAdapter = new EditAdapter(this, guList,nickname);
        rc_edit.setAdapter(mEditAdapter);


    }

    private void guInfo2() {
        layoutManager2 = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        rc_edit2.setLayoutManager(layoutManager2);

        mEditClickAdapter = new EditClickAdapter(this, guList,nickname);
        rc_edit2.setAdapter(mEditClickAdapter);

        helper = new ItemTouchHelper(new ItemTouchHelperCallback(mEditClickAdapter));
        helper.attachToRecyclerView(rc_edit2);
    }


    private void tryGetAddress() {
        Log.d("확인","회원 동네 조회성공1");

        showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getAddress();
    }

    private void tryPostAddress(String gu) {
        Log.d("확인","회원 동네 조회등록1");

        showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.postAddress(new MainPostInfo(gu,"서울특별시"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_backedit:
                finish();
                break;
            case R.id.btn_edit: //편집하기
                if(oneItem) {
                    showCustomToast("내 동네가 2개 이상 있어야 가능해요!");
                }else{
                    btn_edit.setVisibility(View.INVISIBLE);
                    btn_save.setVisibility(View.VISIBLE);
                    btn_add_town.setVisibility(View.INVISIBLE);
                    text.setVisibility(View.INVISIBLE);
                    rc_edit.setVisibility(View.INVISIBLE);
                    rc_edit2.setVisibility(View.VISIBLE);
                    guide.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_save:
                tryGetAddress();

                if(guList.size()==1){
                    btn_add_town.setVisibility(View.VISIBLE);
                    oneItem = true;

                }else{
                    btn_add_town.setVisibility(View.GONE);
                    oneItem = false;
                }
                btn_edit.setVisibility(View.VISIBLE);
                btn_save.setVisibility(View.INVISIBLE);
                text.setVisibility(View.VISIBLE);
                rc_edit.setVisibility(View.VISIBLE);
                rc_edit2.setVisibility(View.INVISIBLE);
                guide.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_add_town:
                Intent add = new Intent(EditTownActivity.this, AddTownActivity.class);
                startActivityForResult(add,REQUEST_TEST2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_TEST2:

                if (resultCode == RESULT_OK) {

                    btn_add_town.setVisibility(View.INVISIBLE);
                    btn_nowlocation2.setVisibility(View.VISIBLE);





                    text_afterchangegu.setText(data.getStringExtra("직접선택구"));
                     String secondGu = data.getStringExtra("직접선택구");


                    tryPostAddress(secondGu);

                    Log.d("확인","직접선택한 구입니다."+secondGu);



                } else {   // RESULT_CANCEL
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }

    @Override
    public void validateHomeSuccess(MainResponse response) {

    }

    @Override
    public void validateDHomeSuccess(MainResponse response) {

    }

    @Override
    public void validateDSuccess(DisasterResponse response) {

    }

    @Override
    public void validatePostSuccess(MainPostResponse response) {
        hideProgressDialog();

        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1200:
                    showCustomToast("새로운 구가 등록되었습니다.");

                    SharedPreferences.Editor editor = sSharedPreferences.edit();

                    editor.putLong(SECOND_ADDRESSIDX, response.getResult().getAddressIdx());

                    editor.apply();

                    break;
            }
        } else {


            showCustomToast("네트워크 연결이 원활하지 않습니다.");

        }

    }

    @Override
    public void validateWeatherSuccess(HomeWeatherResponse response) {

    }

    @Override
    public void validateAddressSuccess(AddressGetResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1202:
                    guList.clear(); //먼저 eventList에 있는 것들을 다 지운다.

                    guList.addAll(response.getResult());



                    if(guList.size()==1){
                        btn_add_town.setVisibility(View.VISIBLE);
                        oneItem = true;

                    }else{
                        btn_add_town.setVisibility(View.GONE);
                        oneItem = false;
                    }

                    mEditClickAdapter.notifyDataSetChanged(); //view차원에서 업데이트
                    mEditAdapter.notifyDataSetChanged(); //view차원에서 업데이트
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