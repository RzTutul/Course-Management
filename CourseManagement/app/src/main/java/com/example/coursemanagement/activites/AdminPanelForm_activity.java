package com.example.coursemanagement.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coursemanagement.R;

public class AdminPanelForm_activity extends AppCompatActivity {

    private CardView AddNewCourseCV,EditCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_form);
        EditCV = findViewById(R.id.EditCV);
        AddNewCourseCV = findViewById(R.id.AddcourseCV);

        AddNewCourseCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanelForm_activity.this, Add_Course_Activity.class);
                startActivity(intent);

            }
        });

        EditCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanelForm_activity.this, Admin_CourseRV_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
