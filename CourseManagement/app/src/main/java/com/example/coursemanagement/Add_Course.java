package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_Course extends AppCompatActivity {

    private EditText courseidET, coursenameET, coursedescpET;
    private Spinner courseCatagoriesSP, EnrollStatusSP;
    private CourseAdpaterRV courseAdpaterRV;
    private String FromDateText = "";
    private String ToDate = "";
    private String [] Catagories;
    private String [] EnrollStatus;

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
        courseidET = findViewById(R.id.CouseID);
        coursenameET = findViewById(R.id.CouseNameID);
        coursedescpET = findViewById(R.id.couseDescID);
        courseCatagoriesSP = findViewById(R.id.catagoriesSP);
        EnrollStatusSP = findViewById(R.id.CoursePaySp);

        Catagories = getResources().getStringArray(R.array.CatagoriesName);
        EnrollStatus = getResources().getStringArray(R.array.EnrollStatus);

        ArrayAdapter<String> Catagoriesadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,Catagories);

        ArrayAdapter<String> ErollAdapeter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,EnrollStatus);


        EnrollStatusSP.setAdapter(ErollAdapeter);
        courseCatagoriesSP.setAdapter(Catagoriesadapter);



    }

    public void saveCourse(View view) {
        String id = courseidET.getText().toString();
        String name = coursenameET.getText().toString();
        String descp = coursedescpET.getText().toString();
        String catagories = null;
        String cousePay = null;

        coursePojo course = new coursePojo(Integer.parseInt(id), name, descp, catagories, cousePay);

        MainActivity.coursePojoList.add(course);
        startActivity(new Intent(Add_Course.this, MainActivity.class));


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


}
