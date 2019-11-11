package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.user_activites.CourseList_MainActivity;
import com.example.coursemanagement.user_activites.Enroll_List_activity;

public class AdminPanelForm_activity extends AppCompatActivity {


    private CardView addNewCourseCV, editCV,enrollCV,catagoriesCV,teacherCV;
    private ScrollView scrollView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
      /*  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );*/


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_form);
        setTitle("Admin Panel");
        editCV = findViewById(R.id.EditCV);
        addNewCourseCV = findViewById(R.id.AddcourseCV);
        enrollCV = findViewById(R.id.enrollCVID);
        catagoriesCV = findViewById(R.id.addCatagoriesCVID);
        teacherCV = findViewById(R.id.AddTeacherCV);
        scrollView = findViewById(R.id.scrollViewID);

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.layout_animation);
        addNewCourseCV.startAnimation(shake);
        editCV.startAnimation(shake);
        catagoriesCV.startAnimation(shake);
        teacherCV.startAnimation(shake);


        addNewCourseCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanelForm_activity.this, Add_Course_Activity.class);
                startActivity(intent);

            }
        });

        editCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanelForm_activity.this, Admin_CourseRV_Activity.class);
                startActivity(intent);

            }
        });
        enrollCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPanelForm_activity.this, AllenrollList_Activity.class);
                startActivity(intent);

            }
        });

        catagoriesCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPanelForm_activity.this, Add_Categories_Activity.class);
                startActivity(intent);

            }
        });

        teacherCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPanelForm_activity.this, All_TeacherList_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void UserModeCV(View view) {
        Intent intent = new Intent(AdminPanelForm_activity.this, CourseList_MainActivity.class);
        startActivity(intent);

    }

    public void WishListCV(View view) {
        Intent intent = new Intent(AdminPanelForm_activity.this, AllenrollList_Activity.class);
        startActivity(intent);
    }
}
