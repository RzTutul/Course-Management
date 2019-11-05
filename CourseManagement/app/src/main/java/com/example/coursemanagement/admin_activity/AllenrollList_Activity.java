package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;
import com.example.coursemanagement.user_activites.Enroll_List_activity;

import java.util.ArrayList;
import java.util.List;

public class AllenrollList_Activity extends AppCompatActivity {

    private TextView enrollTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allenroll_list_);
        enrollTV = findViewById(R.id.EnrollID);

    /*    List<String> enrollListPojos = new ArrayList<>();
        enrollListPojos = CourseDatebase.getInstance(this).getEnrollDao().getAllValue();

        for (String id : enrollListPojos)
        {

            enrollTV.setText(id);
        }*/


    }
}
