package com.example.coursemanagement.user_activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.coursemanagement.adapter.CourseAdpaterRV;
import com.example.coursemanagement.R;
import com.example.coursemanagement.admin_activity.AdminPanelForm_activity;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.shared_preference.UserAuthPreference;

import java.util.List;

public class CourseList_MainActivity extends AppCompatActivity {

    private RecyclerView CourseRV;
    private CourseAdpaterRV courseAdpaterRV;
    public List<Course_Pojo> coursePojoList;
    private UserAuthPreference authPreference;
    private boolean status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseRV = findViewById(R.id.courseRV);

        setTitle("All Courses");
        coursePojoList = CourseDatebase.getInstance(this).getCourseDao().getAllCourse();

        courseAdpaterRV = new CourseAdpaterRV(this, coursePojoList);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        // GridLayoutManager gridLayout = new GridLayoutManager(this,2);
        CourseRV.setLayoutManager(llm);
        CourseRV.setAdapter(courseAdpaterRV);

        authPreference = new UserAuthPreference(this);
        status = authPreference.getLoginStatus();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.login);
        MenuItem logoutItem = menu.findItem(R.id.logout);
        MenuItem Dashboard = menu.findItem(R.id.dashboard);


        if (status) {
            loginItem.setVisible(false);
            logoutItem.setVisible(true);
            Dashboard.setVisible(true);
        } else {
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
            Dashboard.setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.dashboard:
                startActivity(new Intent(CourseList_MainActivity.this, Enroll_List_activity.class));
                break;

            case R.id.Admindashboard:
                startActivity(new Intent(CourseList_MainActivity.this, AdminPanelForm_activity.class));
                break;

            case R.id.login:
                startActivity(new Intent(CourseList_MainActivity.this, LoginFrom_activity.class));
                break;

            case R.id.logout:
                authPreference.SetLoginStatus(false);
                startActivity(new Intent(CourseList_MainActivity.this, LoginFrom_activity.class));
                break;
            case R.id.exit:
                this.finish();

            default:

        }

        return super.onOptionsItemSelected(item);
    }

}
