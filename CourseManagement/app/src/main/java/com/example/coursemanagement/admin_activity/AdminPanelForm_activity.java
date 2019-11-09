package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coursemanagement.R;
import com.example.coursemanagement.user_activites.Enroll_List_activity;

public class AdminPanelForm_activity extends AppCompatActivity {

    private CardView addNewCourseCV, editCV,enrollCV,catagoriesCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_form);
        setTitle("Admin Panel");
        editCV = findViewById(R.id.EditCV);
        addNewCourseCV = findViewById(R.id.AddcourseCV);
        enrollCV = findViewById(R.id.enrollCVID);
        catagoriesCV = findViewById(R.id.addCatagoriesCVID);


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
    }
}
