package com.example.coursemanagement.admin_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.AllEnrollListAdapter;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.joinquerymodel.StudentWithEnrollCourse;

import java.util.List;

public class AllenrollList_Activity extends AppCompatActivity {

    private TextView totalEnrollCounterTV;
    private RecyclerView all_enrollListRV;
    private AllEnrollListAdapter enrollListAdapter;
    private List<StudentWithEnrollCourse> studentWithEnrollCourses;
    private CardView dashboardCV;
    private Spinner courseNameSP;
    private ArrayAdapter<String> courseNameAA;

    private List<String> coursePojos;

    private Context context;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allenroll_list_);

        context = this;
        totalEnrollCounterTV = findViewById(R.id.totalenrollCountTV);
        all_enrollListRV = findViewById(R.id.enrollListRV);
        dashboardCV = findViewById(R.id.dashboardCV);
        courseNameSP = findViewById(R.id.selectCourseName);


        coursePojos = CourseDatebase.getInstance(this).getCourseDao().getAllCourseName();
        coursePojos.add("Select Course");

        courseNameAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, coursePojos);

        courseNameSP.setAdapter(courseNameAA);

        studentWithEnrollCourses = CourseDatebase.getInstance(this).getEnrollDao().getAllstudentWithEnroll();



        enrollListAdapter = new AllEnrollListAdapter(this,studentWithEnrollCourses);
        LinearLayoutManager llm  = new LinearLayoutManager(this);
        all_enrollListRV.setLayoutManager(llm);
        all_enrollListRV.setAdapter(enrollListAdapter);


        courseNameSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String courseName = parent.getItemAtPosition(position).toString();

                if (courseName.equals("Select Course"))
                {

                }
                else
                {
                    List<StudentWithEnrollCourse> std;
                    std= CourseDatebase.getInstance(context).getEnrollDao().getEnrollforSingleUSerFilter(courseName);
                    enrollListAdapter = new AllEnrollListAdapter(context,std);
                    LinearLayoutManager llm = new LinearLayoutManager(context);
                    // GridLayoutManager gridLayout = new GridLayoutManager(this,2);
                    all_enrollListRV.setLayoutManager(llm);
                    all_enrollListRV.setAdapter(enrollListAdapter);
                    long totalCourse= CourseDatebase.getInstance(context).getEnrollDao().getTotalEnrollStudentbyCourse(courseName);
                    totalEnrollCounterTV.setText(String.valueOf(totalCourse));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_allstudent_admin_activty,menu);


        this.menu= menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        MenuItem menuItem = menu.findItem(R.id.filterid);


        switch (item.getItemId())
        {
            case R.id.filterid:

                if (dashboardCV.getVisibility()==View.VISIBLE)
                {
                    menuItem.setIcon(R.drawable.ic_filter_list_black_24dp);
                    int position = courseNameAA.getPosition("Select Course");
                    dashboardCV.setVisibility(View.GONE);

                    enrollListAdapter = new AllEnrollListAdapter(this,studentWithEnrollCourses);
                    LinearLayoutManager llm  = new LinearLayoutManager(this);
                    all_enrollListRV.setLayoutManager(llm);
                    all_enrollListRV.setAdapter(enrollListAdapter);
                    courseNameSP.setSelection(position);


                }
                else
                {
                    dashboardCV.setVisibility(View.VISIBLE);
                    menuItem.setIcon(R.drawable.ic_list_black_24dp);
                    int position = courseNameAA.getPosition("Select Course");
                    courseNameSP.setSelection(position);

                    long totalEnrollStd= CourseDatebase.getInstance(this).getEnrollDao().getTotalEnrollStudent();
                    totalEnrollCounterTV.setText(String.valueOf(totalEnrollStd));




                }

                break;

                default:


        }
        return true;
    }
}
