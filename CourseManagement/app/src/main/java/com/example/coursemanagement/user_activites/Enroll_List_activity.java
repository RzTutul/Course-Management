package com.example.coursemanagement.user_activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.SingleUserEnrollAdapterRV;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Single_UserEnroll_wishList_Pojo;
import com.example.coursemanagement.joinquerymodel.StudentWithEnrollCourse;
import com.example.coursemanagement.shared_preference.UserIdPreference;

import java.util.List;

public class Enroll_List_activity extends AppCompatActivity {

    RecyclerView enrollRV;
    List<Single_UserEnroll_wishList_Pojo> enrollListPojos;
    private SingleUserEnrollAdapterRV adapterRV;
    private UserIdPreference userIdPreference;
    private List<StudentWithEnrollCourse> student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll__list);
        setTitle("Enroll List");
        ///Add Back Button at Action bar..
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        enrollRV = findViewById(R.id.EnrollRV);

        userIdPreference= new UserIdPreference(this);
        long id = userIdPreference.getLoginID();

        student =CourseDatebase.getInstance(this).getEnrollDao().getEnrollforSingleUSer(id);

        adapterRV = new SingleUserEnrollAdapterRV(this, student);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        enrollRV.setLayoutManager(llm);
        enrollRV.setAdapter(adapterRV);

    }
    ///For Back button at action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(Enroll_List_activity.this,CourseList_MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
