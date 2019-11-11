package com.example.coursemanagement.user_activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.StudentInfo_Pojo;
import com.example.coursemanagement.shared_preference.UserAuthPreference;
import com.example.coursemanagement.shared_preference.UserIdPreference;
import com.google.android.material.textfield.TextInputEditText;

public class StudentRegistrationFrom_Activity extends AppCompatActivity {

    private static final int REQUEST_STORAGE_PERMSIONCODE_CODE = 321;
    private static final int GALLERY_REQUEST_CODE = 123;
    TextInputEditText stdNameET, stdEmailET, stdPhoneET, stdPasswordET, stdConfirmPassET;
    private UserAuthPreference userAuthPreference;
    private UserIdPreference userIdPreference;
    private String currentImagePath;
    private ImageView studentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_from);

        setTitle("RegisterFrom");

        stdNameET = findViewById(R.id.stdNameETID);
        stdEmailET = findViewById(R.id.stdEmailETID);
        stdPhoneET = findViewById(R.id.stdPhoneETID);
        stdPasswordET = findViewById(R.id.stdPasswordETID);
        stdConfirmPassET = findViewById(R.id.stdConfirmPassETID);
        studentImage = findViewById(R.id.selectImageView);
    }

    public void Registerbtn(View view) {
        String name = stdNameET.getText().toString();
        String email = stdEmailET.getText().toString();
        String phone = stdPhoneET.getText().toString();
        String pass = stdPasswordET.getText().toString();
        String confrimPass = stdConfirmPassET.getText().toString();

        if (name.equals("")) {
            stdNameET.setError("Give Your Name!");
        }
       else if (email.equals("")) {
            stdEmailET.setError("Give a Email!");
        }
       else if (phone.equals("")) {
            stdPhoneET.setError("Give a Mobile Number!");
        }
       else if (pass.equals("")) {
            stdPasswordET.setError("Give a Password!");
        }
       else if (confrimPass.equals("")) {
            stdConfirmPassET.setError("Give Password Again!");
        }
     else if (pass.equals(confrimPass))
        {
            StudentInfo_Pojo studentInfoPojo = new StudentInfo_Pojo(name,email,phone,pass,currentImagePath);


            long insertStd = CourseDatebase.getInstance(this).getStudentDao().InsertNewStudent(studentInfoPojo);

            long id  = CourseDatebase.getInstance(this).getStudentDao().getId(email,pass);
            if (insertStd>0 && id>0)
            {

                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();
                userAuthPreference = new UserAuthPreference(this);
                userIdPreference = new UserIdPreference(this);
                userAuthPreference.SetLoginStatus(true);
                userIdPreference.setLogin(id);
                startActivity(new Intent(StudentRegistrationFrom_Activity.this,CourseList_MainActivity.class));
                finish();
            }

        }

     else
        {
            stdConfirmPassET.setError("Password isn't matched!");

        }





    }

    public void SelectImageForStudent(View view) {

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
            Bitmap bmp = BitmapFactory.decodeFile(currentImagePath);
            studentImage.setImageBitmap(bmp);

        }

    }

}
