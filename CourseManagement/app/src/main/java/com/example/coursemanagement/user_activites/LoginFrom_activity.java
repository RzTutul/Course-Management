package com.example.coursemanagement.user_activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coursemanagement.R;
import com.example.coursemanagement.admin_activity.AdminPanelForm_activity;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.shared_preference.UserAuthPreference;
import com.example.coursemanagement.shared_preference.UserIdPreference;

public class LoginFrom_activity extends AppCompatActivity {

    private Button loginbtn;
    private EditText emailET,passwordET;
    private UserAuthPreference preference;
    private UserIdPreference userIdPreference;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_from);

        preference = new UserAuthPreference(this);
        userIdPreference = new UserIdPreference(this);
         boolean status = preference.getLoginStatus();
        if (status)
        {
            startActivity(new Intent(LoginFrom_activity.this,Enroll_List_activity.class));
        }



        loginbtn = findViewById(R.id.loginbtn);
        emailET = findViewById(R.id.EmailID);
        passwordET = findViewById(R.id.passwordETID);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailET.getText().toString();
                String pass= passwordET.getText().toString();
                long id = CourseDatebase.getInstance(context).getStudentDao().getId(email,pass);

                if (id>0)
                {
                    userIdPreference.setLogin(id);
                    startActivity(new Intent(LoginFrom_activity.this, CourseList_MainActivity.class));
                    preference.SetLoginStatus(true);
                    finish();
                }
                else
                {
                    Toast.makeText(context, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }



                if (email.equals("admin") && pass.equals("admin"))
                {
                    startActivity(new Intent(LoginFrom_activity.this, AdminPanelForm_activity.class));

                    finish();
                }

            }
        });



    }


    public void SignUp(View view) {

        startActivity(new Intent(LoginFrom_activity.this, StudentRegistrationFrom_Activity.class));
        finish();
    }


}
