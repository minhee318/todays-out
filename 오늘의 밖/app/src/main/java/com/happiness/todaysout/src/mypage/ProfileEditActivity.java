package com.happiness.todaysout.src.mypage;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.happiness.todaysout.R;
import com.happiness.todaysout.src.BaseActivity;
import com.happiness.todaysout.src.login.TownSettingActivity;
import com.happiness.todaysout.src.mypage.Interface.MyView;
import com.happiness.todaysout.src.mypage.model.MyDongResponse;
import com.happiness.todaysout.src.mypage.model.MyResponse;
import com.happiness.todaysout.src.mypage.model.PatchInfo;
import com.happiness.todaysout.src.mypage.model.PatchProfileResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.happiness.todaysout.src.ApplicationClass.NICKNAME;
import static com.happiness.todaysout.src.ApplicationClass.PROFILE_IMAGE;
import static com.happiness.todaysout.src.ApplicationClass.USER_EMAIL;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class ProfileEditActivity extends BaseActivity implements MyView {
    FrameLayout btn_profile;
    EditText edittext_profile;
    TextView btn_next;
    ImageView btn_editback;
    CircleImageView img_profile;
    String profileName;
    boolean checkNickName = false;
    boolean dismiss = false; //?????? ????????????
    TextView btn_camera;
    TextView btn_album;
    TextView btn_basic;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_TAKE_ALBUM = 2;
    private boolean mIsDefaultImage = false;
    String mCurrentPhotoPath;
    Uri downloadUri;
    EditText editText_email;

    String email;

    TextView btn_editsave;//????????????

    private Bitmap mProfileBitmap = null;

    Bitmap icon;
    String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit);

        saveData();

        tryGetMyInfo(sSharedPreferences.getLong("USER_IDX", -1));
        editText_email = findViewById(R.id.editText_email);

        img_profile = findViewById(R.id.img_edit_profile);
        btn_editsave = findViewById(R.id.btn_editsave);
        edittext_profile = findViewById(R.id.edittext_profile);


        if(sSharedPreferences.getString(USER_EMAIL, "gg")!=null){
            editText_email.setText(sSharedPreferences.getString(USER_EMAIL, "gg"));
            email = sSharedPreferences.getString(USER_EMAIL, "gg");

        }else{
            editText_email.setText("");
        }


        edittext_profile.setText(sSharedPreferences.getString("NICKNAME",null));
        profileName = sSharedPreferences.getString("NICKNAME",null);



        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("????????? ????????? ???????????????.")
                .setDeniedMessage("????????? ????????? ?????????????????????.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();


        icon = BitmapFactory.decodeResource(ProfileEditActivity.this.getResources(),
                R.drawable.profile_none);

        btn_editback = findViewById(R.id.btn_editback);


        btn_editback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_profile = findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog profileDialog = new BottomSheetDialog(
                        ProfileEditActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.profile_dialog,
                                (LinearLayout) findViewById(R.id.bottomSheetContainer)
                        );


                btn_camera = bottomSheetView.findViewById(R.id.btn_camera);
                btn_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takePicture();
                        profileDialog.cancel();
                    }

                });

                btn_album = bottomSheetView.findViewById(R.id.btn_album);
                btn_album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //selectAlbum();
                        getAlbum();
                        profileDialog.cancel();
                    }
                });

                btn_basic = bottomSheetView.findViewById(R.id.btn_basic);
                btn_basic.setOnClickListener(v12 -> {

                    img_profile.setImageResource(R.drawable.profile_none);


                    mIsDefaultImage = true;

                    profileDialog.cancel();
                });
                profileDialog.setContentView(bottomSheetView);
                profileDialog.show();
            }
        });

        btn_editsave = findViewById(R.id.btn_editsave);
        btn_editsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (mIsDefaultImage) { //???????????????

                        photo =  BitMapToString(icon);
                        uploadFirebase(icon);
                    } else {

                        if (mProfileBitmap != null) {
                            photo =  BitMapToString(mProfileBitmap);
                            uploadFirebase(mProfileBitmap);
                        }else{
                            photo =  BitMapToString(icon);
                            uploadFirebase(icon);
                        }
                    }


                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString(NICKNAME, profileName);
                    editor.putString(PROFILE_IMAGE,photo);
                    editor.apply();





            }
        });




        editText_email.addTextChangedListener(new TextWatcher() {
            // ???????????? ???????????? ????????? ?????? ???
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText_email.getText().length() == 0) {
                    email = "";
                } else {

                }
            }

            // ????????? ????????? ???
            @Override
            public void afterTextChanged(Editable editable) {
                email = edittext_profile.getText().toString();

            }

            // ???????????? ??????
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });







        edittext_profile.addTextChangedListener(new TextWatcher() {
            // ???????????? ???????????? ????????? ?????? ???
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edittext_profile.getText().length() == 0) {

                } else {

                }
            }

            // ????????? ????????? ???
            @Override
            public void afterTextChanged(Editable editable) {

                profileName = edittext_profile.getText().toString();

            }

            // ???????????? ??????
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    private void uploadFirebase(Bitmap bitmap) {
        showProgressDialog();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        long mNow = System.currentTimeMillis();
        StorageReference profileImagesRef = storageRef.child("Profile/"+  mNow +"_profile_"  + ".jpg");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = profileImagesRef.putBytes(data);

        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return profileImagesRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {


                    downloadUri = task.getResult();
                    Log.d("??????","?????????????????? ??????"+downloadUri);

                    tryPathchMyInfo(email,profileName,downloadUri.toString());


                    hideProgressDialog();
                    finish();

                } else {

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        try {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO: {
                    if (resultCode == RESULT_OK) {


                        File file = new File(mCurrentPhotoPath);
                        Bitmap bitmap = MediaStore.Images.Media
                                .getBitmap(getContentResolver(), Uri.fromFile(file));


                        if (bitmap != null) {
                            ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                    ExifInterface.ORIENTATION_UNDEFINED);

                            Bitmap rotatedBitmap = null;
                            switch (orientation) {

                                case ExifInterface.ORIENTATION_ROTATE_90:
                                    rotatedBitmap = rotateImage(bitmap, 90);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_180:
                                    rotatedBitmap = rotateImage(bitmap, 180);
                                    break;

                                case ExifInterface.ORIENTATION_ROTATE_270:
                                    rotatedBitmap = rotateImage(bitmap, 270);
                                    break;

                                case ExifInterface.ORIENTATION_NORMAL:
                                default:
                                    rotatedBitmap = bitmap;
                            }



                            img_profile.setImageBitmap(rotatedBitmap);


                            mProfileBitmap = rotatedBitmap;
                            mIsDefaultImage = false;
                        }else{

                        }



                    }
                    break;
                }
                case REQUEST_TAKE_ALBUM: {
                    if (requestCode == REQUEST_TAKE_ALBUM && resultCode == RESULT_OK && intent != null && intent.getData() != null) {
                        Uri selectedImageUri = intent.getData();
                        Bitmap bitmap = MediaStore.Images.Media
                                .getBitmap(getContentResolver(), selectedImageUri);
                        if (bitmap != null) {
                            img_profile.setImageBitmap(bitmap);


                            mProfileBitmap = bitmap;
                            mIsDefaultImage = false;
                        }
                    }
                    break;
                }
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

    }




    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            Toast.makeText(getApplicationContext(), "????????? ?????????", Toast.LENGTH_SHORT).show();
            dismiss = false;
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//            Toast.makeText(getApplicationContext(), "????????? ?????????", Toast.LENGTH_SHORT).show();
            dismiss = true;

        }
    };




    private void getAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
    }

    private File createImageFile() throws IOException { //???????????? ????????? ???????????? ????????? ??????????????? ????????? ??????????????????.
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    // ?????? ??????
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    private void takePicture() {
        Log.d("??????","??????1");

        if(dismiss){
            TedPermission.with(getApplicationContext())
                    .setPermissionListener(permissionListener)
                    .setRationaleMessage("????????? ????????? ???????????????.")
                    .setDeniedMessage("????????? ????????? ?????????????????????.")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .check();
        }else{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.softsquared.todaysout",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }
    }

    private void tryGetMyInfo(Long userIdx) {
        showProgressDialog();

        final MyService myService = new MyService(this);
        myService.getMy(userIdx);
    }

    private void tryPathchMyInfo(String email,String nickname,String picture ) {
        showProgressDialog();
        final MyService myService = new MyService(this);

        myService.patchProfile(new PatchInfo(email,nickname,picture));
    }

    @Override
    public void validateMySuccess(MyResponse response) {
        hideProgressDialog();
        if (response.getIsSuccess()) {
            switch (response.getCode()) {
                case 1011:
                    Log.d("??????", "???????????? ?????? ??????");

                    if (response.getResult().getProfile() != null) {

                        Glide
                                .with(this)
                                .load(response.getResult().getProfile())
                                .into(img_profile);


                    }else{
                        img_profile.setImageResource(R.drawable.profile_none);
                        mIsDefaultImage =true;

                    }


                    break;
            }
        } else {
            Log.d("??????", response.getMessage());
            showCustomToast("???????????? ????????? ???????????? ????????????.");


        }
    }

    @Override
    public void validatePatchProfileSuccess(PatchProfileResponse response) {
        Log.d("??????","????????? ?????? ??????");



        SharedPreferences.Editor editor = sSharedPreferences.edit();

        if(mIsDefaultImage==true){
            editor.putString("??????",BitMapToString(icon));
        }else{
            editor.putString("??????",BitMapToString(mProfileBitmap));
        }
        editor.apply();

    }

    @Override
    public void validateMyDongSuccess(MyDongResponse response) {

    }

    @Override
    public void validateMyDong2Success(MyDongResponse response) {

    }

    @Override
    public void validateMyPostDongSuccess(ReportResponse response) {

    }

    @Override
    public void validateMyPostDong2Success(ReportResponse response) {

    }

    @Override
    public void validateByeSuccess(ReportResponse response) {

    }

    @Override
    public void validateFailure(String message) {

    }


    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private void saveData(){

        mProfileBitmap = StringToBitMap(sSharedPreferences.getString("??????",""));



    }


}