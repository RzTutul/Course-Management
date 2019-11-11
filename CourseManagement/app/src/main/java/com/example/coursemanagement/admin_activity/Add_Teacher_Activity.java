package com.example.coursemanagement.admin_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Teacher_Pojo;
import com.example.coursemanagement.java_files.CustomTypefaceSpan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class Add_Teacher_Activity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE =123 ;
    private static final int REQUEST_STORAGE_PERMSIONCODE_CODE =321 ;
    private static final int CALL_REQUEST_CODE = 567;
    private static final String TAG = Add_Teacher_Activity.class.getSimpleName();
    private TextInputEditText teacherNameET, teacherPhoneET,teacherEmailET;
    private String currentImagePath;
    private ImageView TeacherImage;
    private String editedImagePath;
    private Button tUpdatebtn,tSaveBtn;
    private   long id;
    private  Teacher_Pojo teacherPojo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__teacher_);
        teacherNameET = findViewById(R.id.tNameET);
        teacherPhoneET =findViewById(R.id.tPhoneET);
        teacherEmailET  = findViewById(R.id.tEmailET);
        TeacherImage = findViewById(R.id.tImageView);
        tUpdatebtn = findViewById(R.id.tUpadteBtn);
        tSaveBtn = findViewById(R.id.tSvaebtn);

        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/thinfont.otf");
        SpannableStringBuilder SS = new SpannableStringBuilder("Add Teacher");
        SS.setSpan (new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(SS);


        id = getIntent().getLongExtra("id",-1);


        if (id>0)
        {
           Teacher_Pojo teacher_pojoList = CourseDatebase.getInstance(this).getTeacherDao().getTeacherID(id);

            teacherNameET.setText(teacher_pojoList.getT_name());
            teacherPhoneET.setText(teacher_pojoList.getT_phone());
            teacherEmailET.setText(teacher_pojoList.getT_email());
            currentImagePath = teacher_pojoList.getImage();
            Bitmap bitmap = BitmapFactory.decodeFile(currentImagePath);
            TeacherImage.setImageBitmap(bitmap);

            tUpdatebtn.setVisibility(View.VISIBLE);

            tSaveBtn.setVisibility(View.GONE);


        }

        dispatchCallIntent();


    }

    public void SaveTeacherInfo(View view) {
        String name = teacherNameET.getText().toString();
        String phone = teacherPhoneET.getText().toString();
        String email = teacherEmailET.getText().toString();

        Teacher_Pojo teacherPojo = new Teacher_Pojo(name,phone,email,currentImagePath);

       long insert = CourseDatebase.getInstance(this).getTeacherDao().addNewTeacher(teacherPojo);

       if (insert>0)
       {
           startActivity(new Intent(Add_Teacher_Activity.this,All_TeacherList_Activity.class));

       }



    }

    public void SelectTeacherImage(View view) {

        if (IsStroagePermissionAccept()) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
        }
    }

    private boolean IsStroagePermissionAccept() {
        String[] permsionlist = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permsionlist, REQUEST_STORAGE_PERMSIONCODE_CODE);
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(projection[0]);
            currentImagePath = cursor.getString(index);
            Log.e(TAG, "onActivityResult: " + currentImagePath);
            Bitmap bmp = BitmapFactory.decodeFile(currentImagePath);
            TeacherImage.setImageBitmap(bmp);

        }

    }


    private void dispatchCallIntent(){
        Uri phoneUri = Uri.parse("tel:"+123);
        Intent callItent = new Intent(Intent.ACTION_CALL, phoneUri);
        if (callItent.resolveActivity(getPackageManager()) != null){
            if (isCallRequestAccepted()){
                // startActivity(callItent);
            }
        }else{
            Toast.makeText(this, "no component found", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isCallRequestAccepted(){
        String[] permissionList = {Manifest.permission.CALL_PHONE};
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissionList, CALL_REQUEST_CODE);
            return false;

        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CALL_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchCallIntent();
                } else {
                    Toast.makeText(this, "Need Permission", Toast.LENGTH_SHORT).show();
                }
                break;


        }


    }


    public void UpdateTeacherInfo(View view) {

        String name = teacherNameET.getText().toString();
        String phone = teacherPhoneET.getText().toString();
        String email = teacherEmailET.getText().toString();

          teacherPojo = new Teacher_Pojo(id,name,phone,email,currentImagePath);


       int updated=  CourseDatebase.getInstance(this).getTeacherDao().updateTeacher(teacherPojo);
        if (updated>0)
        {
            startActivity(new Intent(Add_Teacher_Activity.this,All_TeacherList_Activity.class));
        }


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Add_Teacher_Activity.this,AdminPanelForm_activity.class));
        this.finish();
        super.onBackPressed();
    }
}
