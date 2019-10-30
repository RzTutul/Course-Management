package com.example.coursemanagement.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.coursemanagement.R;

public class Enroll_List_activity extends AppCompatActivity {

    RecyclerView enrollRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll__list);
        setTitle("Enroll List");
        enrollRV = findViewById(R.id.EnrollRV);


    }
}
