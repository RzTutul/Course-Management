package com.example.coursemanagement.user_activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
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

import java.util.List;

public class CourseList_MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mdrawerLayout;
    private  NavigationView navigationView;
    private ActionBarDrawerToggle mtoggle;
    private RecyclerView CourseRV;
    public CourseAdpaterRV courseAdpaterRV;
    public List<Course_Pojo> coursePojoList;
    private UserAuthPreference authPreference;
    private boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseRV = findViewById(R.id.courseRV);

        setTitle("All Courses");

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


        Toast.makeText(this, ""+status, Toast.LENGTH_SHORT).show();
        if (status) {
            loginItem.setVisible(false);
            logoutItem.setVisible(true);
            userDashBoard.setVisible(true);
        }
        else {
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
            userDashBoard.setVisible(false);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);

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
                Toast.makeText(CourseList_MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                courseAdpaterRV.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }


/*
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.logoutid);
      MenuItem logoutItem = menu.findItem(R.id.logoutid);
        MenuItem Dashboard = menu.findItem(R.id.Admindashboard);
       NavigationView login = menu.findItem(R.id.loginID);

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
        switch (item.getItemId()) {

            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();

                break;

            case R.id.filter:
                Toast.makeText(this, "Filtered", Toast.LENGTH_SHORT).show();

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
