package com.example.coursemanagement.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.StudentInfo_Pojo;
import com.example.coursemanagement.shared_preference.UserAuthPreference;
import com.google.android.material.textfield.TextInputEditText;

public class StudentRegistrationFrom_Activity extends AppCompatActivity {

    TextInputEditText stdNameET, stdEmailET, stdPhoneET, stdPasswordET, stdConfirmPassET;
    private UserAuthPreference userAuthPreference;

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
            StudentInfo_Pojo studentInfoPojo = new StudentInfo_Pojo(name,email,phone,pass);


            long insertStd = CourseDatebase.getInstance(this).getStudentDao().InsertNewStudent(studentInfoPojo);

            if (insertStd>0)
            {
                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();
                userAuthPreference = new UserAuthPreference(this);
                userAuthPreference.SetLoginStatus(true);
                startActivity(new Intent(StudentRegistrationFrom_Activity.this,CourseList_MainActivity.class));

                finish();
            }

        }

     else
        {
            stdConfirmPassET.setError("Password isn't matched!");

        }





    }
}
