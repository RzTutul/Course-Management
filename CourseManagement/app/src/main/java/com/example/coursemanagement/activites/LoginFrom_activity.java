package com.example.coursemanagement.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursemanagement.R;
import com.example.coursemanagement.shared_preference.UserAuthPreference;

public class LoginFrom_activity extends AppCompatActivity {

    private Button loginbtn;
    private EditText emailET,passwordET;
    private UserAuthPreference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_from);

        preference = new UserAuthPreference(this);

   /*     boolean status = preference.getLoginStatus();
        if (status)
        {
            startActivity(new Intent(LoginFrom_activity.this,Enroll_List_activity.class));
        }*/

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
                    startActivity(new Intent(LoginFrom_activity.this, AdminPanelForm_activity.class));

                    finish();
                }

            }
        });



    }

    public void SignUp(View view) {

        startActivity(new Intent(LoginFrom_activity.this, StudentRegistrationFrom_Activity.class));
    }
}
