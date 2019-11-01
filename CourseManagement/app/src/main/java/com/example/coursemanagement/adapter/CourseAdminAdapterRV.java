package com.example.coursemanagement.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.activites.Add_Course_Activity;
import com.example.coursemanagement.activites.Enroll_List_activity;
import com.example.coursemanagement.activites.LoginFrom_activity;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.shared_preference.UserAuthPreference;

import java.util.List;

public   class CourseAdminAdapterRV extends RecyclerView.Adapter<CourseAdminAdapterRV.CourseViewHolder>{

    private Context context;
    private List<Course_Pojo> coursePojoList;
    private UserAuthPreference userAuthPreference;
    private String modifyText ;
    Bitmap bmp;

    public CourseAdminAdapterRV(Context context, List<Course_Pojo> coursePojoList) {
        this.context = context;
        this.coursePojoList = coursePojoList;


    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.admin_course_list_row, parent,false);
        return new CourseAdminAdapterRV.CourseViewHolder(view) ;


    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, final int position) {
        holder.courseName.setText(coursePojoList.get(position).getCourseName());
        holder.courseDesp.setText(coursePojoList.get(position).getCourseDesc());
        holder.courseDuration.setText(" "+coursePojoList.get(position).getCourseDuration());
        holder.totalTime.setText(coursePojoList.get(position).getTotalHours());
        holder.courseCost.setText(String.valueOf(coursePojoList.get(position).getCourseCost()) );
        bmp = BitmapFactory.decodeFile(coursePojoList.get(position).getImage());
        holder.courseImage.setImageBitmap(bmp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Course Details");
                builder.setIcon(R.drawable.dateicon);
                LayoutInflater inflater = LayoutInflater.from(context);

                View view1 = inflater.inflate(R.layout.admin_edit_dilog,null);
                final ImageView CourseImage = view1.findViewById(R.id.aDialog_courseImage);
                final  TextView CourseName = view1.findViewById(R.id.aDialog_courseNameTV);
                CourseName.setText(coursePojoList.get(position).getCourseName());
                Bitmap bm = BitmapFactory.decodeFile(coursePojoList.get(position).getImage());
                CourseImage.setImageBitmap(bm);

                final Button Editbtn = view1.findViewById(R.id.dialog_editbtn);
                final Button Deletebtn = view1.findViewById(R.id.dialog_deletebtn);


                builder.setView(view1);
                final AlertDialog dialog = builder.create();
                dialog.show();

                Editbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Course_Pojo coursePojo = coursePojoList.get(position);

                        long id = coursePojo.getCourseID();

                        Intent intent = new Intent(context, Add_Course_Activity.class);
                        Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();

                        intent.putExtra("id",id);
                        context.startActivity(intent);
                    }
                });

                Deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return coursePojoList.size();
    }


    public class CourseViewHolder extends RecyclerView.ViewHolder
    {
        TextView courseName,courseDesp,courseDuration,totalTime,courseCost;

        ImageView courseImage;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.row_A_courseName);
            courseDesp = itemView.findViewById(R.id.row_A_courseDesp);
            courseDuration = itemView.findViewById(R.id.row_A_courseDuration);
            totalTime = itemView.findViewById(R.id.row_A_total_time);
            courseCost = itemView.findViewById(R.id.row_A_Cost);
            courseImage= itemView.findViewById(R.id.row_A_CourseImage);

        }
    }




}
