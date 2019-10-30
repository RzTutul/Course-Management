package com.example.coursemanagement.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_Course_Activity extends AppCompatActivity {

    private EditText courseidET, coursenameET, coursedescpET,totalHours,totalCost;
    private Spinner courseCatagoriesSP, EnrollStatusSP;
    private String FromDateText = "";
    private String ToDate = "";
    private String [] Catagories;
    private String [] EnrollStatus;
    private String Course_Catagories ="";
    private  String Course_EnrollStatus ="";
    private String cost;
    private String imagePath=null;

    private DatePickerDialog.OnDateSetListener setDateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(i, i1, i2);

                    Date date = calendar.getTime();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");

                    FromDateText = simpleDateFormat.format(date);

                    Button FromDate = findViewById(R.id.fromDatebtn);

                    FromDate.setText(FromDateText);


                }
            };

    private DatePickerDialog.OnDateSetListener SetToDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(i, i1, i2);

            Date date = calendar.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
            Button toDate = findViewById(R.id.toDatebtn);

            ToDate = sdf.format(date);
            toDate.setText(ToDate);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__course);


        coursenameET = findViewById(R.id.CouseNameID);
        coursedescpET = findViewById(R.id.couseDescID);
        totalHours = findViewById(R.id.totalHoursID);
        totalCost = findViewById(R.id.costID);

        courseCatagoriesSP = findViewById(R.id.catagoriesSP);
        EnrollStatusSP = findViewById(R.id.CoursePaySp);



        Catagories = getResources().getStringArray(R.array.CatagoriesName);
        EnrollStatus = getResources().getStringArray(R.array.EnrollStatus);

        ArrayAdapter<String> Catagoriesadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,Catagories);

        ArrayAdapter<String> ErollAdapeter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,EnrollStatus);


        EnrollStatusSP.setAdapter(ErollAdapeter);
        courseCatagoriesSP.setAdapter(Catagoriesadapter);


        courseCatagoriesSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Course_Catagories = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        EnrollStatusSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Course_EnrollStatus = adapterView.getItemAtPosition(i).toString();

                if (Course_EnrollStatus.equals("Free"))
                {
                    totalCost.setText("0");
                    cost = "0";
                }
                else
                {
                    totalCost.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void saveCourse(View view) {

        String name = coursenameET.getText().toString();
        String descp = coursedescpET.getText().toString();
        String duration = FromDateText+"-"+ToDate;
        String totalTime = totalHours.getText().toString();
          cost = totalCost.getText().toString();
        String Catagories = Course_Catagories;
        String EnrollStatus = Course_EnrollStatus;

        Course_Pojo course = new Course_Pojo(name, descp,imagePath, duration,totalTime,Catagories,EnrollStatus,Integer.parseInt(cost));


        final long insertRow = CourseDatebase.getInstance(this).getCourseDao().InsertNewCourse(course);

        if (insertRow>0)
        {
            startActivity(new Intent(Add_Course_Activity.this, MainActivity.class));

            finish();
        }



    }

    public void SelectFromDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, setDateListener, year, month, day);
        datePickerDialog.show();
    }

    public void SelectToDate(View view) {

        Calendar calendar = Calendar.getInstance();

        int Year = calendar.get(Calendar.YEAR);
        int Month = calendar.get(Calendar.MONTH);
        int Day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, SetToDateListener, Year, Month, Day);

        dialog.show();

    }


    public void SelectImage(View view) {
    }
}
