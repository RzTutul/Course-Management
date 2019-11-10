package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Teacher_Pojo;
import com.google.android.material.textfield.TextInputEditText;

public class Add_Teacher_Activity extends AppCompatActivity {

    TextInputEditText teacherNameET, teacherPhoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__teacher_);
        teacherNameET = findViewById(R.id.teacherNameID);
        teacherPhoneET =findViewById(R.id.teacherPhoneID);

    }

    public void SaveTeacherInfo(View view) {
        String name = teacherNameET.getText().toString();
        String phone = teacherPhoneET.getText().toString();

        Teacher_Pojo teacherPojo = new Teacher_Pojo(name,phone);

       long insert = CourseDatebase.getInstance(this).getTeacherDao().addNewTeacher(teacherPojo);

       if (insert>0)
       {
           startActivity(new Intent(Add_Teacher_Activity.this,All_TeacherList_Activity.class));

       }



    }
}
