package com.example.coursemanagement.user_activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursemanagement.adapter.CourseAdpaterRV;
import com.example.coursemanagement.R;
import com.example.coursemanagement.admin_activity.AdminPanelForm_activity;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.shared_preference.UserAuthPreference;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class CourseList_MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mdrawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mtoggle;
    private RecyclerView CourseRV;
    public CourseAdpaterRV courseAdpaterRV;
    public List<Course_Pojo> coursePojoList;
    private UserAuthPreference authPreference;
    private boolean status;
    private Spinner catagoriesSp;
    private List<String> Catagories;
    private ArrayAdapter<String> Catagoriesadapter;
    private Menu menu;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseRV = findViewById(R.id.courseRV);
        catagoriesSp = findViewById(R.id.cata_spinner);

        setTitle("All Courses");
        context = this;

        // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        mdrawerLayout = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mtoggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        coursePojoList = CourseDatebase.getInstance(this).getCourseDao().getAllCourse();

        courseAdpaterRV = new CourseAdpaterRV(this, coursePojoList);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        // GridLayoutManager gridLayout = new GridLayoutManager(this,2);
        CourseRV.setLayoutManager(llm);
        CourseRV.setAdapter(courseAdpaterRV);

        authPreference = new UserAuthPreference(this);
        status = authPreference.getLoginStatus();

        Menu menuNav = navigationView.getMenu();

        MenuItem loginItem = menuNav.findItem(R.id.loginID);
        MenuItem logoutItem = menuNav.findItem(R.id.logoutid);
        //  MenuItem AdminDash = menuNav.findItem(R.id.Admindashboard);
        MenuItem userDashBoard = menuNav.findItem(R.id.userDashboard);


        if (status) {
            loginItem.setVisible(false);
            logoutItem.setVisible(true);
            userDashBoard.setVisible(true);
        } else {
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
            userDashBoard.setVisible(false);
        }


        Catagories = CourseDatebase.getInstance(this).getCatagoriesDao().getCatagories();
        Catagoriesadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Catagories);
        Catagoriesadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Catagories);
        catagoriesSp.setAdapter(Catagoriesadapter);

        catagoriesSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Course_Catagories;
                Course_Catagories = parent.getItemAtPosition(position).toString();

                if (Course_Catagories.equals("Select Categories"))
                {
                    courseAdpaterRV = new CourseAdpaterRV(context, coursePojoList);
                    LinearLayoutManager llm = new LinearLayoutManager(context);
                    // GridLayoutManager gridLayout = new GridLayoutManager(this,2);
                    CourseRV.setLayoutManager(llm);
                    CourseRV.setAdapter(courseAdpaterRV);

                }
                else
                {
                    List<Course_Pojo> coursePojos;
                    coursePojos= CourseDatebase.getInstance(context).getCourseDao().getCouseByCatagories(Course_Catagories);

                    courseAdpaterRV = new CourseAdpaterRV(context, coursePojos);

                    LinearLayoutManager llm = new LinearLayoutManager(context);
                    // GridLayoutManager gridLayout = new GridLayoutManager(this,2);
                    CourseRV.setLayoutManager(llm);
                    CourseRV.setAdapter(courseAdpaterRV);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);


        /*   For Spinnner*/


     /*   MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.CatagoriesName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);*/

        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setQueryHint("search by course name");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                courseAdpaterRV.getFilter().filter(newText);
                return false;
            }
        });


        //this.menu = menu is used For only change for filtericon
        this.menu = menu;

        return true;
    }


/*
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.logoutid);
      MenuItem logoutItem = menu.findItem(R.id.logoutid);
        MenuItem Dashboard = menu.findItem(R.id.Admindashboard);

        if (status) {
            loginItem.setVisible(false);
            logoutItem.setVisible(true);
            Dashboard.setVisible(true);
        } else {
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
            Dashboard.setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }
*/


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }

        MenuItem filter = menu.findItem(R.id.filter);

        switch (item.getItemId()) {


            case R.id.filter:


                if (catagoriesSp.getVisibility() == View.VISIBLE )
                {
                    catagoriesSp.setVisibility(View.GONE);
                    filter.setIcon(R.drawable.ic_filter_list_black_24dp);
                    courseAdpaterRV = new CourseAdpaterRV(context, coursePojoList);
                    LinearLayoutManager llm = new LinearLayoutManager(context);
                    // GridLayoutManager gridLayout = new GridLayoutManager(this,2);
                    CourseRV.setLayoutManager(llm);
                    CourseRV.setAdapter(courseAdpaterRV);


                    catagoriesSp.setSelection(0);



                }
                else
                {
                    catagoriesSp.setVisibility(View.VISIBLE);
                    filter.setIcon(R.drawable.ic_list_black_24dp);

                }

                break;


            default:

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.userDashboard:
                startActivity(new Intent(CourseList_MainActivity.this, Enroll_List_activity.class));
                break;

            case R.id.Admindashboard:
                startActivity(new Intent(CourseList_MainActivity.this, AdminPanelForm_activity.class));
                break;
            case R.id.ShareID:
                Intent intent = new Intent((Intent.ACTION_SEND));
                intent.setType("text/plain");
                String subject = "Bell of English App";
                String Body = "This is apps help you Lean English Sentences Regularly. Download from this link http://rztutultechtunes.blogspot.com";

                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                startActivity(Intent.createChooser(intent, "Share with"));
                break;

            case R.id.loginID:
                startActivity(new Intent(CourseList_MainActivity.this, LoginFrom_activity.class));
                break;

            case R.id.logoutid:
                authPreference.SetLoginStatus(false);
                startActivity(new Intent(CourseList_MainActivity.this, LoginFrom_activity.class));
                break;
            case R.id.exitID:
                this.finish();

            default:

        }

        return false;
    }
}
