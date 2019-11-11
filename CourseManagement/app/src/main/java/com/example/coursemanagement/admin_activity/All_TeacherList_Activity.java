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
import com.example.coursemanagement.adapter.TeacherListAdapter;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Teacher_Pojo;
import com.example.coursemanagement.java_files.CustomTypefaceSpan;

import java.util.List;

public class All_TeacherList_Activity extends AppCompatActivity {

    private RecyclerView TeacherRV;
    private TeacherListAdapter teacherListAdapter;
    private List<Teacher_Pojo> teacher_pojos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__teacher_list_);
        TeacherRV = findViewById(R.id.TeacherRV);

        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/thinfont.otf");
        SpannableStringBuilder SS = new SpannableStringBuilder("All Teachers");
        SS.setSpan (new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(SS);

        ///Add Back Button at Action bar..
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        teacher_pojos = CourseDatebase.getInstance(this).getTeacherDao().getAllTeacher();

        teacherListAdapter =new  TeacherListAdapter(this,teacher_pojos);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        TeacherRV.setLayoutManager(llm);
        TeacherRV.setAdapter(teacherListAdapter);



    }

    public void AddTeacher(View view) {
        startActivity(new Intent(All_TeacherList_Activity.this,Add_Teacher_Activity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(All_TeacherList_Activity.this,AdminPanelForm_activity.class));
        this.finish();
        super.onBackPressed();
    }

    ///For Back button at action bar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(All_TeacherList_Activity.this, AdminPanelForm_activity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
