package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminPanelForm extends AppCompatActivity {

    private CardView AddNewCourseCV,EditCV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_form);
        EditCV = findViewById(R.id.EditCV);

        EditCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanelForm.this,MainActivity.class);
                intent.putExtra("modify","modify");
                startActivity(intent);
            }
        });
    }
}
