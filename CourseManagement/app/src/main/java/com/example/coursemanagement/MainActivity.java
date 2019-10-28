package com.example.coursemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

        setTitle("All Courses");
        coursePojoList = CourseDatebase.getInstance(this).getCourseDao().getAllCourse();

        Bundle extras = getIntent().getExtras();
        String user;

        if (extras != null) {
            user = extras.getString("modify");
            Toast.makeText(this, "Admin Mode ON", Toast.LENGTH_SHORT).show();
            setTitle("DashBoard");
            courseAdpaterRV = new CourseAdpaterRV(this,coursePojoList,user);
        }
        else {
            courseAdpaterRV = new CourseAdpaterRV(this, coursePojoList, "User");
             }


        LinearLayoutManager lmm = new LinearLayoutManager(this);

        CourseRV.setLayoutManager(lmm);

        CourseRV.setAdapter(courseAdpaterRV);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.login:
                startActivity(new Intent(MainActivity.this,LoginFrom.class));
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
