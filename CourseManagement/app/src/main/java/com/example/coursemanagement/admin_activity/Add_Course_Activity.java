package com.example.coursemanagement.admin_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
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
import com.example.coursemanagement.entitites.Categories_Pojo;
import com.example.coursemanagement.entitites.Course_Pojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Add_Course_Activity extends AppCompatActivity {
    private static final String TAG = Add_Course_Activity.class.getSimpleName();

    private static final int GALLERY_REQUEST_CODE = 123;
    private static final int REQUEST_STORAGE_PERMSIONCODE_CODE = 321;
    private EditText courseidET, coursenameET, coursedescpET, totalHours, totalCost;
    private Spinner courseCatagoriesSP, EnrollStatusSP;
    private String FromDateText = "";
    private String ToDate = "";
    private List<String> Catagories;
    private String[] EnrollStatus;
    private String Course_Catagories = "";
    private String Course_EnrollStatus = "";
    private String cost;
    private String imagePath;
    private ImageView CourseImage;
    private Button savebtn, UpdateBtn, FromDate, toDate;
    private Context context = this;
    private String id;
    private ArrayAdapter<String> Catagoriesadapter;


    private DatePickerDialog.OnDateSetListener setDateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(i, i1, i2);

                    Date date = calendar.getTime();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");

                    FromDateText = simpleDateFormat.format(date);


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

            ToDate = sdf.format(date);
            toDate.setText(ToDate);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__course);
        courseidET = findViewById(R.id.couseID);
        coursenameET = findViewById(R.id.CouseNameID);
        coursedescpET = findViewById(R.id.couseDescID);
        totalHours = findViewById(R.id.totalHoursID);
        totalCost = findViewById(R.id.costID);
        savebtn = findViewById(R.id.SaveBtn);
        UpdateBtn = findViewById(R.id.updatebtn);
        FromDate = findViewById(R.id.fromDatebtn);
        toDate = findViewById(R.id.toDatebtn);


        courseCatagoriesSP = findViewById(R.id.catagoriesSP);
        EnrollStatusSP = findViewById(R.id.CoursePaySp);

        CourseImage = findViewById(R.id.CourseImage);
        Catagories = CourseDatebase.getInstance(this).getCatagoriesDao().getCatagories();

        if (Catagories.isEmpty())
        {

            Categories_Pojo defaultval = new Categories_Pojo("null","Select Categories");
            Categories_Pojo cata1 = new Categories_Pojo("Java Porgramming Basic","Programming Language");
            Categories_Pojo cata2 = new Categories_Pojo("Mobile App development Basic","Mobile App Development");
            Categories_Pojo cata3 = new Categories_Pojo("Web App development","Web  Development");
            Categories_Pojo cata4 = new Categories_Pojo("Game development","Game Development");
            Categories_Pojo cata5 = new Categories_Pojo("Graphic Desing","Desing");
            CourseDatebase.getInstance(this).getCatagoriesDao().addNewCatagories(defaultval);
            CourseDatebase.getInstance(this).getCatagoriesDao().addNewCatagories(cata1);
            CourseDatebase.getInstance(this).getCatagoriesDao().addNewCatagories(cata2);
            CourseDatebase.getInstance(this).getCatagoriesDao().addNewCatagories(cata3);
            CourseDatebase.getInstance(this).getCatagoriesDao().addNewCatagories(cata4);
            CourseDatebase.getInstance(this).getCatagoriesDao().addNewCatagories(cata5);

        }



       // Catagories = getResources().getStringArray(R.array.CatagoriesName);


        EnrollStatus = getResources().getStringArray(R.array.EnrollStatus);

       Catagoriesadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Catagories);

        ArrayAdapter<String> ErollAdapeter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, EnrollStatus);

        EnrollStatusSP.setAdapter(ErollAdapeter);
        courseCatagoriesSP.setAdapter(Catagoriesadapter);

        UpdateBtn.setVisibility(View.GONE);


        id = getIntent().getStringExtra("id");

        if (id != null) {
            Course_Pojo coursePojo = CourseDatebase.getInstance(this).getCourseDao().getCourseID(id);
            courseidET.setText(coursePojo.getCourseID());
            coursenameET.setText(coursePojo.getCourseName());
            coursedescpET.setText(coursePojo.getCourseDesc());
            Bitmap bmp = BitmapFactory.decodeFile(coursePojo.getImage());
            CourseImage.setImageBitmap(bmp);

            String Duration = coursePojo.getCourseDuration();
            String[] value = Duration.split("-");
            FromDate.setText(value[0]);
            toDate.setText(value[1]);

            totalHours.setText(coursePojo.getTotalHours());

            String C_catagories = coursePojo.getCourseCatagories(); //the value you want the position for
            int spinnerPosition = Catagoriesadapter.getPosition(C_catagories);
            courseCatagoriesSP.setSelection(spinnerPosition);

            String C_Cost = coursePojo.getCourseErollStatus();
            int pos = ErollAdapeter.getPosition(C_Cost);
            EnrollStatusSP.setSelection(pos);

            totalCost.setText(String.valueOf(coursePojo.getCourseCost()));

            savebtn.setVisibility(View.GONE);
            UpdateBtn.setVisibility(View.VISIBLE);

        }

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


        String c_id = courseidET.getText().toString();
        String name = coursenameET.getText().toString();
        String descp = coursedescpET.getText().toString();
        String duration = FromDateText + "-" + ToDate;
        String totalTime = totalHours.getText().toString();
        cost = totalCost.getText().toString();
        String Catagories = Course_Catagories;
        String EnrollStatus = Course_EnrollStatus;

        Course_Pojo course = new Course_Pojo(c_id,name, descp, imagePath, duration, totalTime, Catagories, EnrollStatus, Integer.parseInt(cost));


        final long insert = CourseDatebase.getInstance(this).getCourseDao().InsertNewCourse(course);

        if (insert > 0) {
            Intent intent = new Intent(Add_Course_Activity.this, Admin_CourseRV_Activity.class);
            startActivity(intent);

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

        if (IsStroagePermissionAccept()) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
        }
    }

    private boolean IsStroagePermissionAccept() {
        String[] permsionlist = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permsionlist, REQUEST_STORAGE_PERMSIONCODE_CODE);
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(projection[0]);
            imagePath = cursor.getString(index);
            Log.e(TAG, "onActivityResult: " + imagePath);
            Bitmap bmp = BitmapFactory.decodeFile(imagePath);
            CourseImage.setImageBitmap(bmp);

        }

    }


    public void UpdateCourse(View view) {

        String name = coursenameET.getText().toString();
        String descp = coursedescpET.getText().toString();
        String Fromdate = FromDate.getText().toString();//Button
        String Todate = toDate.getText().toString();//Button
        String duration = Fromdate +"-"+ Todate;
        String totalTime = totalHours.getText().toString();
        cost = totalCost.getText().toString();
        String Catagories = Course_Catagories;
        String EnrollStatus = Course_EnrollStatus;


        Course_Pojo course = new Course_Pojo(id,name, descp, imagePath, duration, totalTime, Catagories, EnrollStatus, Integer.parseInt(cost));


        final int Updated = CourseDatebase.getInstance(this).getCourseDao().UpdateCourse(course);

        Toast.makeText(this, ""+Updated, Toast.LENGTH_SHORT).show();
        if (Updated > 0) {
            Intent intent = new Intent(Add_Course_Activity.this, Admin_CourseRV_Activity.class);
            startActivity(intent);

            finish();
        }

    }
}
