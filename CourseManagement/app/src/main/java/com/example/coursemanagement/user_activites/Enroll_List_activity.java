package com.example.coursemanagement.user_activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.EnrollAdapterRV;
import com.example.coursemanagement.entitites.Single_UserEnroll_wishList_Pojo;

import java.util.List;

public class Enroll_List_activity extends AppCompatActivity {

    RecyclerView enrollRV;
    List<Single_UserEnroll_wishList_Pojo> enrollListPojos;
    private EnrollAdapterRV adapterRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll__list);
        setTitle("Enroll List");
        enrollRV = findViewById(R.id.EnrollRV);

        long id = getIntent().getLongExtra("id",-1);
        
        if (id>0)
        {
     ///   enrollListPojos = CourseDatebase.getInstance(this).getSinguserDao().getAllValues(id);
        adapterRV = new EnrollAdapterRV(this,enrollListPojos);
        }
        LinearLayoutManager llm = new LinearLayoutManager(this);

        enrollRV.setLayoutManager(llm);
        enrollRV.setAdapter(adapterRV);

    }
}
