package com.example.coursemanagement.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_Course_Activity extends AppCompatActivity {
    private static final String TAG = Add_Course_Activity.class.getSimpleName();

    private static final int GALLERY_REQUEST_CODE =123 ;
    private static final int REQUEST_STORAGE_PERMSIONCODE_CODE =321 ;
    private EditText courseidET, coursenameET, coursedescpET,totalHours,totalCost;
    private Spinner courseCatagoriesSP, EnrollStatusSP;
    private String FromDateText = "";
    private String ToDate = "";
    private String [] Catagories;
    private String [] EnrollStatus;
    private String Course_Catagories ="";
    private  String Course_EnrollStatus ="";
    private String cost;
    private String imagePath;
    private ImageView CourseImage;


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
        long id =0;
        id = getIntent().getLongExtra("id",-1);

        coursenameET = findViewById(R.id.CouseNameID);
        coursedescpET = findViewById(R.id.couseDescID);
        totalHours = findViewById(R.id.totalHoursID);
        totalCost = findViewById(R.id.costID);

        courseCatagoriesSP = findViewById(R.id.catagoriesSP);
        EnrollStatusSP = findViewById(R.id.CoursePaySp);

        CourseImage = findViewById(R.id.CourseImage);


        Catagories = getResources().getStringArray(R.array.CatagoriesName);
        EnrollStatus = getResources().getStringArray(R.array.EnrollStatus);

        ArrayAdapter<String> Catagoriesadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,Catagories);

        ArrayAdapter<String> ErollAdapeter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,EnrollStatus);


        Toast.makeText(this, "AddCourse"+id, Toast.LENGTH_SHORT).show();
        if (id>0)
        {
            Course_Pojo coursePojo = CourseDatebase.getInstance(this).getCourseDao().getCourseID(id);
            coursenameET.setText(coursePojo.getCourseName());
            coursedescpET.setText(coursePojo.getCourseDesc());
            totalHours.setText(coursePojo.getTotalHours());
            String C_catagories =coursePojo.getCourseCatagories(); //the value you want the position for

            Toast.makeText(this, ""+C_catagories, Toast.LENGTH_SHORT).show();
      /*      ArrayAdapter myAdap = (ArrayAdapter) courseCatagoriesSP.getAdapter(); //cast to an ArrayAdapter

            int spinnerPosition = myAdap.getPosition(C_catagories);

//set the default according to value
            courseCatagoriesSP.setSelection(spinnerPosition);*/
            totalCost.setText(String.valueOf(coursePojo.getCourseCost()));
        }
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
            Intent intent = new Intent(Add_Course_Activity.this, Admin_CourseRV_Activity.class);
            startActivity(intent);

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

        if (IsStroagePermissionAccept())
        {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
        }
    }

    private boolean IsStroagePermissionAccept(){
        String[] permsionlist={Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(permsionlist,REQUEST_STORAGE_PERMSIONCODE_CODE);
            return false;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK){
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(),projection, null, null, null);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(projection[0]);
            imagePath = cursor.getString(index);
           Log.e(TAG, "onActivityResult: "+imagePath);
            Bitmap bmp = BitmapFactory.decodeFile(imagePath);
            CourseImage.setImageBitmap(bmp);

        }

    }

}
