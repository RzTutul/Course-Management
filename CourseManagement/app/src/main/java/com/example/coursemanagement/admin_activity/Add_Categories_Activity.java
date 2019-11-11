package com.example.coursemanagement.admin_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Categories_Pojo;
import com.example.coursemanagement.java_files.CustomTypefaceSpan;
import com.google.android.material.textfield.TextInputEditText;

public class Add_Categories_Activity extends AppCompatActivity {

    private Button categoriesSavebtn;
    private TextInputEditText categoriesET;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__categories_);

        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/thinfont.otf");
        SpannableStringBuilder SS = new SpannableStringBuilder("Add Categories");
        SS.setSpan (new CustomTypefaceSpan("", font2), 0, SS.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(SS);

        ///Add Back Button at Action bar..
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        context = this;

        categoriesSavebtn = findViewById(R.id.cataSavebtnID);
        categoriesET = findViewById(R.id.cataETID);

        categoriesSavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String catagories = categoriesET.getText().toString();

                if (catagories.isEmpty())
                {
                    categoriesET.setError("Enter categories!");
                }
                else
                {
                    Categories_Pojo categoriesPojo = new Categories_Pojo(catagories);

                    long id  = CourseDatebase.getInstance(context).getCatagoriesDao().addNewCatagories(categoriesPojo);

                    if (id>0)
                    {
                        Toast.makeText(Add_Categories_Activity.this, "Saved Successful" , Toast.LENGTH_SHORT).show();

                        categoriesET.setText("");
                    }
                }

            }
        });


    }


    ///For Back button at action bar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(Add_Categories_Activity.this, AdminPanelForm_activity.class);
            startActivity(intent);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
