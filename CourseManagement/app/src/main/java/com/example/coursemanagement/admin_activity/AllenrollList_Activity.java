package com.example.coursemanagement.admin_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursemanagement.R;
import com.example.coursemanagement.adapter.AllEnrollListAdapter;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.EmailBox_Pojo;
import com.example.coursemanagement.entitites.MessageBox_Pojo;
import com.example.coursemanagement.joinquerymodel.StudentWithEnrollCourse;

import java.util.List;

public class AllenrollList_Activity extends AppCompatActivity {

    private static final int CALL_REQUEST_CODE = 123;
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
    private  String messagesText;
    private String emailBody;
    private String emailText;

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


        dispatchCallIntent();


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


    private void dispatchCallIntent(){
        Uri phoneUri = Uri.parse("tel:"+123);
        Intent callItent = new Intent(Intent.ACTION_CALL, phoneUri);
        if (callItent.resolveActivity(getPackageManager()) != null){
            if (isCallRequestAccepted()){
               // startActivity(callItent);
            }
        }else{
            Toast.makeText(this, "no component found", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CALL_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchCallIntent();
                } else {
                    Toast.makeText(this, "Need Permission", Toast.LENGTH_SHORT).show();
                }
                break;

        }


    }

    private boolean isCallRequestAccepted(){
        String[] permissionList = {Manifest.permission.CALL_PHONE};
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissionList, CALL_REQUEST_CODE);
            return false;

        }
        return true;
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
                    Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                    dashboardCV.startAnimation(shake);

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

    public void setMessageText(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Set Message");
        builder.setIcon(R.drawable.messagegroupicon);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.message_dilog,null);

       final EditText message = view1.findViewById(R.id.messageET);
        Button save = view1.findViewById(R.id.saveMessagebtn);
        builder.setView(view1);

        final AlertDialog dialog = builder.create();
        dialog.show();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messagesText= message.getText().toString();
                MessageBox_Pojo messageBoxPojo = new MessageBox_Pojo(messagesText);
                CourseDatebase.getInstance(context).getMessageBoxDao().AddNewMessage(messageBoxPojo);


                Toast.makeText(context, "Message Set!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


    }

    public void setGmailText(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Set Message");
        builder.setIcon(R.drawable.messagegroupicon);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.email_dialog,null);

        final EditText subject = view1.findViewById(R.id.emailTextET);
        final EditText composeEmail = view1.findViewById(R.id.bodyET);

        Button savebtn = view1.findViewById(R.id.saveEmailbtn);
        builder.setView(view1);

        final AlertDialog dialog = builder.create();
        dialog.show();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subj = subject.getText().toString();
                String email = composeEmail.getText().toString();

                EmailBox_Pojo emailBoxPojo = new EmailBox_Pojo(subj,email);

                CourseDatebase.getInstance(context).getEmailDao().InsertNewEmail(emailBoxPojo);

                Toast.makeText(context, "Email Set Successfully! ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();


            }
        });



    }
}
