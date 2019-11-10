package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.TeacherListAdapter;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Teacher_Pojo;

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

        teacher_pojos = CourseDatebase.getInstance(this).getTeacherDao().getAllTeacher();

        teacherListAdapter =new  TeacherListAdapter(this,teacher_pojos);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        TeacherRV.setLayoutManager(llm);
        TeacherRV.setAdapter(teacherListAdapter);






    }

    public void AddTeacher(View view) {
        startActivity(new Intent(All_TeacherList_Activity.this,Add_Teacher_Activity.class));
    }
}
