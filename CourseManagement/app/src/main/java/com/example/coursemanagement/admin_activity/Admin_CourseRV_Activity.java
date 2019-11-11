package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.CourseAdminAdapterRV;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.java_files.CustomTypefaceSpan;

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

        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/thinfont.otf");
        SpannableStringBuilder SS = new SpannableStringBuilder("Course Modify");
        SS.setSpan (new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(SS);


        ///Add Back Button at Action bar..
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    ///For Back button at action bar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(Admin_CourseRV_Activity.this, AdminPanelForm_activity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
