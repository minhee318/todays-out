package com.softsquared.todaysout.src.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softsquared.todaysout.R;
import com.softsquared.todaysout.src.BaseActivity;
import com.softsquared.todaysout.src.login.Interface.LoginActivityView;
import com.softsquared.todaysout.src.login.models.AddressInfos;
import com.softsquared.todaysout.src.login.models.LoginData;
import com.softsquared.todaysout.src.login.models.LoginResponse;
import com.softsquared.todaysout.src.login.models.SignInResponse;
import com.softsquared.todaysout.src.main.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.softsquared.todaysout.src.ApplicationClass.FIRST_ADDRESSIDX;
import static com.softsquared.todaysout.src.ApplicationClass.NICKNAME;
import static com.softsquared.todaysout.src.ApplicationClass.PROFILE_IMAGE;
import static com.softsquared.todaysout.src.ApplicationClass.SECOND_ADDRESSIDX;
import static com.softsquared.todaysout.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.todaysout.src.ApplicationClass.USER_IDX;
import static com.softsquared.todaysout.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.todaysout.src.ApplicationClass.sSharedPreferences;

public class TownSettingActivity extends BaseActivity implements View.OnClickListener, LoginActivityView {
    ImageView btn_townback;
    LinearLayout btn_townsetting;
    LinearLayout btn_nowlocation;
    LinearLayout btn_nowlocation2; //직접 선택시 바뀌는 레이아웃
    TextView btn_nextgreen;
    TextView text_afterchangegu;
    TextView text_gu;
    TextView text_1;
    TextView text_2;
    TextView text_3;
    String token;
    SharedPreferences.Editor editor = sSharedPreferences.edit();
    ArrayList<Integer> addressidxList = new ArrayList<>();

    //AddressInfos [] list = new AddressInfos[2];

    ArrayList<AddressInfos> addressList = new ArrayList<>();

    String url;
    String secondGu = null;

    String snsId;

    private final int REQUEST_TEST = 1; //case문에서 사용하는 상수는 fianl로 해야함

    private GpsTracker gpsTracker;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_setting);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {

                            return;
                        }
                        token = task.getResult();
                        Log.d("확인","fcm토큰: "+token);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 원활하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                });


        Intent getIntent = getIntent();
        snsId = getIntent.getStringExtra("snsId");
        url = getIntent.getStringExtra("url");

        Log.d("확인",snsId);

        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {

            checkRunTimePermission();
        }


        gpsTracker = new GpsTracker(this);

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        address = getCurrentAddress(latitude, longitude);


        btn_townback = findViewById(R.id.btn_townback);
        btn_townback.setOnClickListener(this);
        btn_townsetting = findViewById(R.id.btn_townsetting);
        btn_townsetting.setOnClickListener(this);
        btn_nextgreen = findViewById(R.id.btn_nextgreen);
        btn_nextgreen.setOnClickListener(this);
        btn_nowlocation = findViewById(R.id.btn_nowlocation);
        btn_nowlocation2 = findViewById(R.id.btn_nowlocation2);
        text_afterchangegu = findViewById(R.id.text_afterchangegu);
        text_gu = findViewById(R.id.text_gu);
        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);
        text_3 = findViewById(R.id.text_3);

        Log.d("확인",address);

        if(address.equals("주소 미발견")){
            finish();
        }else{
            if(address.substring(5,10).equals("서울특별시")){
                text_gu.setText(address.substring(11,14));
            }else{
                showCustomToast("서울이 아니시군요!");
            }
        }


        addressList.add(new AddressInfos("서울특별시",address.substring(11,14),""));
        //list[0]= new AddressInfos("서울","강서구","");

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("확인", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;


            case REQUEST_TEST:

                if (resultCode == RESULT_OK) {

                    btn_townsetting.setVisibility(View.INVISIBLE);
                    btn_nowlocation2.setVisibility(View.VISIBLE);
                    text_gu.setTextColor(Color.parseColor("#000000"));
                    text_1.setTextColor(Color.parseColor("#A5A5A5"));
                    text_2.setTextColor(Color.parseColor("#A5A5A5"));
                    text_3.setTextColor(Color.parseColor("#A5A5A5"));

                    btn_nowlocation.setBackground(ContextCompat.getDrawable(getApplication(), R.drawable.locationsetting));

                    btn_nextgreen.setText("가입완료");
                    text_afterchangegu.setText(data.getStringExtra("직접선택구"));
                    secondGu = data.getStringExtra("직접선택구");

                    Log.d("확인","직접선택한 구입니다."+secondGu);
                    addressList.add(new AddressInfos("서울특별시",secondGu,""));


                } else {   // RESULT_CANCEL
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

                }

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                //위치 값을 가져올 수 있음
                ;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }



        if (addresses == null || addresses.size() == 0) {
           // Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
          //  return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    private void postSignInfo() {
        showProgressDialog();
        final LoginService signInService = new LoginService(this);

        signInService.postSignIn(new LoginData(sSharedPreferences.getString(NICKNAME, null),url,addressList,token,snsId));
        Log.d("확인",sSharedPreferences.getString(NICKNAME, null)+" "+url+" "+snsId+" "+addressList);

    }



    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_townback:
               finish();
                break;
            case R.id.btn_townsetting:
                Intent town = new Intent(TownSettingActivity.this, SearchActivity.class);
                startActivityForResult(town,REQUEST_TEST);
                break;
            case R.id.btn_nextgreen:
                //회원가입 API

                    postSignInfo();


                break;

        }

    }

    @Override
    public void validateKakaoSuccess(LoginResponse response) {

    }

    @Override
    public void validateAutoLoginSuccess(int code) {

    }

    @Override
    public void validateSignInSuccess(SignInResponse response) {
        Log.d("확인","회원가입"+response.getCode());
        hideProgressDialog();
        if(response.getIsSuccess()) {

            switch (response.getCode()) {
                case 1012:

                    Log.d("확인", "회원가입 성공!");

                    editor.putString(X_ACCESS_TOKEN, response.getResult().getJwt());

                    Log.d("확인", response.getResult().getJwt());

                    editor.putLong(USER_IDX, response.getResult().getUserIdx());

                    addressidxList.addAll(response.getResult().getAddressIds());

                    editor.putLong(FIRST_ADDRESSIDX, addressidxList.get(0));

                    editor.putLong(SECOND_ADDRESSIDX, addressidxList.get(1));

                    Log.d("확인", "addressIdx확인:" + addressidxList.get(0) + "---" + addressList.get(1));

                    editor.apply();

                    Intent main = new Intent(TownSettingActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();

                    break;

                case 3011:

                    Log.d("확인",response.getMessage());

//                    editor.putString(X_ACCESS_TOKEN, response.getResult().getJwt());

                    Log.d("확인", response.getResult().getJwt());

                    editor.putLong(USER_IDX, response.getResult().getUserIdx());

                    addressidxList.addAll(response.getResult().getAddressIds());

                    editor.putLong(FIRST_ADDRESSIDX, addressidxList.get(0));

                    editor.putLong(SECOND_ADDRESSIDX, addressidxList.get(1));

                    Log.d("확인", "addressIdx확인:" + addressidxList.get(0) + "---" + addressList.get(1));

                    editor.apply();

                    Intent main2 = new Intent(TownSettingActivity.this, MainActivity.class);
                    startActivity(main2);
                    finish();

                    break;

            }


            }else{


                showCustomToast("네트워크 연결이 원활하지 않습니다.");
                Log.d("확인", "회원가입 실패" + response.getCode());
                Log.d("확인", response.getMessage());


            }
        }



    @Override
    public void validateFailure(String message) {
        hideProgressDialog();


    }
}