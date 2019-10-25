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
    public static List<coursePojo> coursePojoList;

    static {
      coursePojoList = new ArrayList<>();

      coursePojoList.add(new coursePojo(123,"Mobile App","Mobile App design and Ui Design ","Development","Free"));
      coursePojoList.add(new coursePojo(123,"Graphic Design","Graphic Design Advance Lelve","Design","Free"));
      coursePojoList.add(new coursePojo(123,"PHP Web Design","PHP Design For Beginner Level","Web Desing","Free"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseRV = findViewById(R.id.courseRV);

        courseAdpaterRV = new CourseAdpaterRV(this,coursePojoList);

        LinearLayoutManager lmm = new LinearLayoutManager(this);

        CourseRV.setLayoutManager(lmm);

        CourseRV.setAdapter(courseAdpaterRV);

    }
}
