package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.AllEnrollListAdapter;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;
import com.example.coursemanagement.joinquerymodel.StudentWithEnrollCourse;
import com.example.coursemanagement.user_activites.Enroll_List_activity;

import java.util.ArrayList;
import java.util.List;

public class AllenrollList_Activity extends AppCompatActivity {

    private TextView enrollTV;
    private RecyclerView all_enrollListRV;
    private AllEnrollListAdapter enrollListAdapter;
    private List<StudentWithEnrollCourse> studentWithEnrollCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allenroll_list_);
        enrollTV = findViewById(R.id.EnrollID);
        all_enrollListRV = findViewById(R.id.enrollListRV);


        studentWithEnrollCourses = CourseDatebase.getInstance(this).getEnrollDao().getAllstudentWithEnroll();

        enrollListAdapter = new AllEnrollListAdapter(this,studentWithEnrollCourses);

        LinearLayoutManager llm  = new LinearLayoutManager(this);
        all_enrollListRV.setLayoutManager(llm);
        all_enrollListRV.setAdapter(enrollListAdapter);


    }
}
