package com.happiness.todaysout.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.messaging.FirebaseMessaging;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.alarm.AlarmActivity;
import com.happiness.todaysout.src.main.Adapter.MainAdapter;
import com.happiness.todaysout.src.main.interfaces.MainActivityView;
import com.happiness.todaysout.src.main.models.AddressGetResponse;
import com.happiness.todaysout.src.main.models.DisasterResponse;
import com.happiness.todaysout.src.main.models.HomeInfo;
import com.happiness.todaysout.src.main.models.HomeWeatherResponse;
import com.happiness.todaysout.src.main.models.MainInfo;
import com.happiness.todaysout.src.main.models.MainPostResponse;
import com.happiness.todaysout.src.main.models.MainResponse;
import com.happiness.todaysout.src.main.models.WeatherInfo;
import com.happiness.todaysout.src.main.models.dInfo;
import com.happiness.todaysout.src.mypage.MyPageActivity;

import java.util.ArrayList;

import static com.happiness.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.happiness.todaysout.src.ApplicationClass.USER_IDX;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements View.OnClickListener, MainActivityView,NavigationView.OnNavigationItemSelectedListener{

    ImageView btn_add;
    ViewPager2 main_viewpager;
    ImageView img_alarm;
    ImageView img_mypage;
    ArrayList<HomeInfo> homeList = new ArrayList<>();
    ArrayList<WeatherInfo> weatherList = new ArrayList<>();
    ArrayList<MainInfo> mainList = new ArrayList<>();
    ArrayList<dInfo> DList = new ArrayList<>();
    int Jtotal;
    int Jtotal2;
    String one;
    String second;

    String up1;
    String down1;
    String weatherMent1;
    String weatherFirstMent1;
    String weatherPicture1;
    String alarmFirstMent1;
    String weatherHeartNumber1;
    String alarmHeartNumber1;
    String weatherOndo1;

    String nameGu1;
    String dust1;


    String up2;
    String down2;
    String weatherMent2;
    String weatherFirstMent2;
    String weatherPicture2;
    String alarmFirstMent2;
    String weatherHeartNumber2;
    String alarmHeartNumber2;
    String weatherOndo2;

    String nameGu2;
    String dust2;
    MainAdapter mMainAdapter;
    String token;
    ImageView menu;

    DrawerLayout drawerLayout;
    NavigationView navigationView;



    @Override
    protected void onResume() {

        tryGetWeather();
        tryGetD();
        tryGetBoard();
        tryGetDBoard();


        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(this);

        drawerLayout = findViewById(R.id.dl_main_drawer_root);
        navigationView = findViewById(R.id.nv_main_navigation_root);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        Log.d("??????","userIdx"+sSharedPreferences.getLong(USER_IDX,-1));


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }
                        token = task.getResult();
                        Log.d("??????", "fcm??????: " + token);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "???????????? ????????? ???????????? ????????????.", Toast.LENGTH_LONG).show();
                    }
                });


        main_viewpager = findViewById(R.id.main_viewpager);

        img_alarm = findViewById(R.id.img_alarm);
        img_alarm.setOnClickListener(this);
        img_mypage = findViewById(R.id.img_mypage);
        img_mypage.setOnClickListener(this);

//
        tryGetWeather();
        tryGetD();
        tryGetBoard();
        tryGetDBoard();

        main();

    }

    private void main() {
        mMainAdapter = new MainAdapter(this, mainList, main_viewpager);

        main_viewpager.setAdapter(mMainAdapter);
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.indicator);
        new TabLayoutMediator(tabLayout2, main_viewpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        }).attach();
    }

    private void tryGetBoard() {
        Log.d("??????", "????????? ????????????1");

        // showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getHomeBoard();
    }

    private void tryGetDBoard() {
        Log.d("??????", "?????? ????????? ????????????1");

        // showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getDBoard();
    }

    private void tryGetWeather() {
        Log.d("??????", "?????? ????????????1");

        showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getHomeWeather();
    }


    private void tryGetD() {
        showProgressDialog();
        final MainService mainService = new MainService(this);
        mainService.getD();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_alarm:
                Intent write = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(write);
                break;
            case R.id.img_mypage:
                Intent mypage = new Intent(MainActivity.this, MyPageActivity.class);
                mypage.putExtra("userIdx", sSharedPreferences.getLong("USER_IDX", -1));
                startActivity(mypage);
                break;
            case R.id.menu:
                        drawerLayout.openDrawer(GravityCompat.START);
                break;

        }
    }









    @Override
    public void validateHomeSuccess(MainResponse response) {
        hideProgressDialog();

        if (response.getIsSuccess()) {
            Log.d("??????", "????????? ????????????3");
            switch (response.getCode()) {
                case 1222:
                    //homeList.clear(); //?????? eventList??? ?????? ????????? ??? ?????????.

                    // homeList.addAll(response.getResult()); //api?????? ????????? ????????? ?????? ?????????.


                    if (response.getResult().get(0).getIsExistent().equals("Y")) {
                        weatherFirstMent1 = response.getResult().get(0).getMsg();
                        weatherHeartNumber1 = response.getResult().get(0).getHeartNum();
                    } else {
                        weatherFirstMent1 = "?????? ????????? ?????? ?????????";
                        weatherHeartNumber1 = response.getResult().get(0).getHeartNum();
                    }

                    if(response.getResult().size() != 1){
                        if (response.getResult().get(1).getThirdAddressName() != null) {
                            if (response.getResult().get(1).getIsExistent().equals("Y")) {
                                weatherFirstMent1 = response.getResult().get(1).getMsg();
                                weatherHeartNumber1 = response.getResult().get(1).getHeartNum();
                            } else {
                                weatherFirstMent1 = "?????? ????????? ?????? ?????????";
                                weatherHeartNumber1 = response.getResult().get(1).getHeartNum();
                            }


                        }
                    }




                    Log.d("??????", "main??? ?????????:" + mainList.size());

                    break;
            }
        } else {
            Log.d("??????", response.getMessage());

            showCustomToast("???????????? ????????? ???????????? ????????????.");


        }
    }

    @Override
    public void validateDHomeSuccess(MainResponse response) {
        hideProgressDialog();

        if (response.getIsSuccess()) {
            Log.d("??????", "??????????????? ????????????3");
            switch (response.getCode()) {
                case 1222:


                    // homeList.addAll(response.getResult()); //api?????? ????????? ????????? ?????? ?????????.


                    if (response.getResult().get(0).getIsExistent().equals("Y")) {
                        alarmFirstMent1 = response.getResult().get(0).getMsg();
                        alarmHeartNumber1 = response.getResult().get(0).getHeartNum();
                    } else {
                        alarmFirstMent1 = "?????? ????????? ?????? ?????????";
                        alarmHeartNumber1 = response.getResult().get(0).getHeartNum();
                    }

                    if(response.getResult().size() !=1){
                        if (response.getResult().get(1).getThirdAddressName() != null) {
                            if (response.getResult().get(1).getIsExistent().equals("Y")) {
                                alarmFirstMent2 = response.getResult().get(1).getMsg();
                                alarmHeartNumber2 = response.getResult().get(1).getHeartNum();
                            } else {
                                alarmFirstMent2 = "?????? ????????? ?????? ?????????";
                                alarmHeartNumber2 = response.getResult().get(1).getHeartNum();
                            }


                        }

                    }

                    Log.d("??????", "alarmFirstMent1:"+alarmFirstMent1);



                    break;
            }
        } else {
            Log.d("??????", response.getMessage());

            showCustomToast("???????????? ????????? ???????????? ????????????.");


        }
    }

    @Override
    public void validateDSuccess(DisasterResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1303:

                    Log.d("??????", "?????? ????????????3");
                    DList.clear(); //?????? eventList??? ?????? ????????? ??? ?????????.

                    if (response.getResult().size() != 1) {
                        Jtotal = response.getResult().get(0).getTotal();
                        Jtotal2 = response.getResult().get(1).getTotal();
                        one = response.getResult().get(0).getKind();
                        second = response.getResult().get(1).getKind();
                    } else {
                        Jtotal = response.getResult().get(0).getTotal();
                        one = response.getResult().get(0).getKind();
                    }




                    break;
            }
        } else {


            showCustomToast("???????????? ????????? ???????????? ????????????.");

        }
    }

    @Override
    public void validatePostSuccess(MainPostResponse response) {

    }

    @Override
    public void validateWeatherSuccess(HomeWeatherResponse response) {
        //  hideProgressDialog();
        Log.d("??????", "?????? ????????????3" + response.getCode());
        if (response.getIsSuccess()) {
            Log.d("??????", "?????? ????????????3" + response.getCode());
            switch (response.getCode()) {
                case 1221:

                    mainList.clear(); //?????? eventList??? ?????? ????????? ??? ?????????.


                    up1 = response.getResult().get(0).getUp();
                    down1 = response.getResult().get(0).getDown();
                    if (response.getResult().get(0).getSkyState().equals("1") && response.getResult().get(0).getRain().equals("0")) {
                        weatherMent1 = "?????????";
                        weatherPicture1 = "1";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("0")) {
                        weatherMent1 = "????????? ?????????";
                        weatherPicture1 = "1";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("1")) {
                        weatherMent1 = "????????? ??????\n?????? ??????";
                        weatherPicture1 = "7";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("2")) {
                        weatherMent1 = "????????? ??????\n??????\n?????? ??????";
                        weatherPicture1 = "7";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("3")) {
                        weatherMent1 = "????????? ??????\n?????? ??????";
                        weatherPicture1 = "8";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("4")) {
                        weatherMent1 = "????????? ??????\n???????????? ??????";
                        weatherPicture1 = "10";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("5")) {
                        weatherMent1 = "????????? ??????\n????????????\n????????????";
                        weatherPicture1 = "7";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("6")) {
                        weatherMent1 = "????????? ??????\n???????????????\n???????????? ?????????";
                        weatherPicture1 = "7";
                    } else if (response.getResult().get(0).getSkyState().equals("3") && response.getResult().get(0).getRain().equals("7")) {
                        weatherMent1 = "????????? ??????\n????????????\n?????????";
                        weatherPicture1 = "8";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("0")) {
                        weatherMent1 = "?????????";
                        weatherPicture1 = "4";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("1")) {
                        weatherMent1 = "?????????\n?????? ??????";
                        weatherPicture1 = "5";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("2")) {
                        weatherMent1 = "?????????\n??????\n?????? ??????";
                        weatherPicture1 = "5";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("3")) {
                        weatherMent1 = "?????????\n?????? ??????";
                        weatherPicture1 = "6";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("4")) {
                        weatherMent1 = "?????????\n???????????? ??????";
                        weatherPicture1 = "5";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("5")) {
                        weatherMent1 = "?????????\n????????????\n????????????";
                        weatherPicture1 = "5";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("6")) {
                        weatherMent1 = "?????????\n???????????????\n???????????? ?????????";
                        weatherPicture1 = "5";
                    } else if (response.getResult().get(0).getSkyState().equals("4") && response.getResult().get(0).getRain().equals("7")) {
                        weatherMent1 = "?????????\n????????????\n?????????";
                        weatherPicture1 = "6";
                    }

                    weatherOndo1 = response.getResult().get(0).getNowWeather();
                    nameGu1 = response.getResult().get(0).getSecondAddressName();
                    dust1 = response.getResult().get(0).getDust();


                    mainList.add(new MainInfo(up1, down1, weatherMent1, weatherFirstMent1, weatherPicture1, alarmFirstMent1, weatherHeartNumber1, alarmHeartNumber1, weatherOndo1, Jtotal, nameGu1, dust1, sSharedPreferences.getLong(FIRST_ADDRESSIDX, -1), one));


                    if (response.getResult().size() != 1) {
                        up2 = response.getResult().get(1).getUp();
                        down2 = response.getResult().get(1).getDown();
                        if (response.getResult().get(1).getSkyState().equals("1") && response.getResult().get(1).getRain().equals("0")) {
                            weatherMent2 = "?????????";
                            weatherPicture2 = "1";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("0")) {
                            weatherMent2 = "????????? ?????????";
                            weatherPicture2 = "3";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("1")) {
                            weatherMent2 = "????????? ?????? ?????? ??????";
                            weatherPicture2 = "7";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("2")) {
                            weatherMent2 = "????????? ?????? ?????? ?????? ??????";
                            weatherPicture2 = "7";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("3")) {
                            weatherMent2 = "????????? ?????? ?????? ??????";
                            weatherPicture2 = "8";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("4")) {
                            weatherMent2 = "????????? ?????? ???????????? ??????";
                            weatherPicture2 = "10";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("5")) {
                            weatherMent2 = "????????? ?????? ???????????? ????????????";
                            weatherPicture2 = "7";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("6")) {
                            weatherMent2 = "????????? ?????? ??????????????? ???????????? ?????????";
                            weatherPicture2 = "7";
                        } else if (response.getResult().get(1).getSkyState().equals("3") && response.getResult().get(1).getRain().equals("7")) {
                            weatherMent2 = "????????? ?????? ???????????? ?????????";
                            weatherPicture2 = "8";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("0")) {
                            weatherMent2 = "?????????";
                            weatherPicture2 = "4";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("1")) {
                            weatherMent2 = "????????? ?????? ??????";
                            weatherPicture2 = "5";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("2")) {
                            weatherMent2 = "????????? ?????? ?????? ??????";
                            weatherPicture2 = "5";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("3")) {
                            weatherMent2 = "????????? ?????? ??????";
                            weatherPicture2 = "6";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("4")) {
                            weatherMent2 = "????????? ???????????? ??????";
                            weatherPicture2 = "5";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("5")) {
                            weatherMent2 = "????????? ???????????? ????????????";
                            weatherPicture2 = "5";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("6")) {
                            weatherMent2 = "????????? ??????????????? ???????????? ?????????";
                            weatherPicture2 = "5";
                        } else if (response.getResult().get(1).getSkyState().equals("4") && response.getResult().get(1).getRain().equals("7")) {
                            weatherMent2 = "????????? ???????????? ?????????";
                            weatherPicture2 = "6";
                        }


                        weatherOndo2 = response.getResult().get(1).getNowWeather();
                        nameGu2 = response.getResult().get(1).getSecondAddressName();
                        dust2 = response.getResult().get(1).getDust();

                        mainList.add(new MainInfo(up2, down2, weatherMent2, weatherFirstMent2, weatherPicture2, alarmFirstMent2, weatherHeartNumber2, alarmHeartNumber2, weatherOndo2, Jtotal2, nameGu2, dust2, sSharedPreferences.getLong(SECOND_ADDRESSIDX, -1), second));

                    }

                    mMainAdapter.notifyDataSetChanged(); //view???????????? ????????????





                    //  mMainAdapter.notifyDataSetChanged(); //view???????????? ????????????
                    break;
            }
        } else {
            Log.d("??????", response.getCode() + response.getMessage());
            showCustomToast("???????????? ????????? ???????????? ????????????.");


        }
    }

    @Override
    public void validateAddressSuccess(AddressGetResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "???????????? ?????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(this, "????????? ?????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(this, "?????? ?????????", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;



    }
}
