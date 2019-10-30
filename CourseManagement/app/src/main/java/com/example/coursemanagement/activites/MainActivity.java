package com.example.coursemanagement.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.coursemanagement.adapter.CourseAdpaterRV;
import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView CourseRV;
    private CourseAdpaterRV courseAdpaterRV;
    public List<Course_Pojo> coursePojoList;
    private Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseRV = findViewById(R.id.courseRV);

        setTitle("All Courses");
        coursePojoList = CourseDatebase.getInstance(this).getCourseDao().getAllCourse();

        extras = getIntent().getExtras();


        if (extras != null) {
            String user = extras.getString("modify");
            if (user == null)
            {
                courseAdpaterRV = new CourseAdpaterRV(this, coursePojoList, "user");

            }
            else {
                Toast.makeText(this, "Admin Mode ON" + user, Toast.LENGTH_SHORT).show();
                setTitle("DashBoard");
                courseAdpaterRV = new CourseAdpaterRV(this, coursePojoList, user);
            }
            }
        else {
            courseAdpaterRV = new CourseAdpaterRV(this, coursePojoList, "user");

        }


        LinearLayoutManager llm = new LinearLayoutManager(this);

       // GridLayoutManager gridLayout = new GridLayoutManager(this,2);

        CourseRV.setLayoutManager(llm);

        CourseRV.setAdapter(courseAdpaterRV);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                startActivity(new Intent(MainActivity.this, LoginFrom_activity.class));
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
