package com.happiness.todaysout.src.login;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.happiness.todaysout.src.ApplicationClass.NICKNAME;
import static com.happiness.todaysout.src.ApplicationClass.PROFILE_IMAGE;
import static com.happiness.todaysout.src.ApplicationClass.sSharedPreferences;

public class ProfileActivity extends BaseActivity {
    FrameLayout btn_profile;
    EditText edittext_profile;
    TextView btn_next;
    ImageView btn_loginback;
    CircleImageView img_profile;
    String profileName;
    boolean checkNickName = false;
    boolean dismiss = false; //권한 설정여부
    TextView btn_camera;
    TextView btn_album;
    TextView btn_basic;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_TAKE_ALBUM = 2;
    private boolean mIsDefaultImage = false;
    String mCurrentPhotoPath;
    Uri downloadUri;

    private Bitmap mProfileBitmap = null;

    Bitmap icon;
    String photo;
    String snsId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        img_profile = findViewById(R.id.img_profile);

        Intent getIntent = getIntent();
        snsId = getIntent.getStringExtra("snsId");


        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setDeniedMessage("카메라 권한을 거부하셨습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();


        icon = BitmapFactory.decodeResource(ProfileActivity.this.getResources(),
                R.drawable.profile_none);

        btn_loginback = findViewById(R.id.btn_loginback);
        btn_loginback.setOnClickListener(new View.OnClickListener() {
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
                        ProfileActivity.this, R.style.BottomSheetDialogTheme
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

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkNickName){

                    if (mIsDefaultImage) { //기본이미지

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




                }else{
                    showCustomToast("닉네임을 입력해주세요!");
                }
            }
        });

        edittext_profile = findViewById(R.id.edittext_profile);
        edittext_profile.addTextChangedListener(new TextWatcher() {
            // 입력되는 텍스트에 변화가 있을 때
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edittext_profile.getText().length() == 0) {
                    checkNickName = false;
                    btn_next.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    btn_next.setTextColor(Color.parseColor("#A5A5A5"));
                    btn_next.setEnabled(false);
                } else {
                    checkNickName = true;
                    btn_next.setBackgroundColor(Color.parseColor("#78DECD"));
                    btn_next.setTextColor(Color.parseColor("#FFFFFF"));
                    btn_next.setBackgroundResource(R.drawable.nextbuttongreen);
                    btn_next.setEnabled(true);
                }
            }

            // 입력이 끝났을 때
            @Override
            public void afterTextChanged(Editable editable) {
                btn_next.setBackgroundColor(Color.parseColor("#78DECD"));
                btn_next.setTextColor(Color.parseColor("#FFFFFF"));
                btn_next.setBackgroundResource(R.drawable.nextbuttongreen);
                btn_next.setEnabled(true);
                profileName = edittext_profile.getText().toString();

            }

            // 입력하기 전에
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
                    Log.d("확인","파이어베이스 성공"+downloadUri);

                    Intent profile = new Intent(ProfileActivity.this, TownSettingActivity.class);
                    profile.putExtra("snsId",snsId);
                    profile.putExtra("url",downloadUri.toString());
                    startActivity(profile);


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
//            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
            dismiss = false;
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//            Toast.makeText(getApplicationContext(), "권한이 거부됨", Toast.LENGTH_SHORT).show();
            dismiss = true;

        }
    };




    private void getAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
    }

    private File createImageFile() throws IOException { //카메라로 촬영한 이미지를 파일로 저장해주는 함수를 만들어줍니다.
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

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    private void takePicture() {
        Log.d("확인","사진1");

        if(dismiss){
            TedPermission.with(getApplicationContext())
                    .setPermissionListener(permissionListener)
                    .setRationaleMessage("카메라 권한이 필요합니다.")
                    .setDeniedMessage("카메라 권한을 거부하셨습니다.")
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
                            "com.happiness.todaysout",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }
    }

}