package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.CourseAdminAdapterRV;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;

import java.util.List;

public class Admin_CourseRV_Activity extends AppCompatActivity {

    private RecyclerView A_CourseRV;
    private CourseAdminAdapterRV courseAdpaterRV;
    public List<Course_Pojo> coursePojoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__course_rv_);
        A_CourseRV = findViewById(R.id.Course_RV_Admin);

        setTitle("DASHBOARD");
        coursePojoList = CourseDatebase.getInstance(this).getCourseDao().getAllCourse();
        courseAdpaterRV = new CourseAdminAdapterRV(this, coursePojoList);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        // GridLayoutManager gridLayout = new GridLayoutManager(this,2);
        A_CourseRV.setLayoutManager(llm);
        A_CourseRV.setAdapter(courseAdpaterRV);
    }

    public void AddCourse(View view) {
        startActivity(new Intent(Admin_CourseRV_Activity.this,Add_Course_Activity.class));
    }
}
