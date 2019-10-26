package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView CourseRV;
    private CourseAdpaterRV courseAdpaterRV;
    public  List<coursePojo> coursePojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseRV = findViewById(R.id.courseRV);

        coursePojoList = CourseDatebase.getInstance(this).getCourseDao().getAllCourse();

        courseAdpaterRV = new CourseAdpaterRV(this,coursePojoList);

        LinearLayoutManager lmm = new LinearLayoutManager(this);

        CourseRV.setLayoutManager(lmm);

        CourseRV.setAdapter(courseAdpaterRV);

    }
}
