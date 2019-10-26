package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginFrom extends AppCompatActivity {

    private Button loginbtn;
    private EditText emailET,passwordET;
    private UserAuthPreference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_from);

        preference = new UserAuthPreference(this);

        boolean status = preference.getLoginStatus();
        if (status)
        {
            startActivity(new Intent(LoginFrom.this,Enroll_List.class));
        }

        loginbtn = findViewById(R.id.loginbtn);
        emailET = findViewById(R.id.EmailID);
        passwordET = findViewById(R.id.passwordETID);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailET.getText().toString();
                String pass= passwordET.getText().toString();

                if (email.equals("admin") && pass.equals("admin"))
                {
                    startActivity(new Intent(LoginFrom.this,Add_Course.class));

                    preference.SetLoginStatus(true);
                }
            }
        });



    }

    public void SignUp(View view) {

        startActivity(new Intent(LoginFrom.this,StudentRegistrationFrom.class));
    }
}
