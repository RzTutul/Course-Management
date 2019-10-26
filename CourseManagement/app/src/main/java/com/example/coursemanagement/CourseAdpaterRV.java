package com.example.coursemanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CourseAdpaterRV extends RecyclerView.Adapter<CourseAdpaterRV.CourseViewHolder>{

    private Context context;
    private List<coursePojo> coursePojoList;
    private UserAuthPreference userAuthPreference;

    public CourseAdpaterRV(Context context, List<coursePojo> coursePojoList) {
        this.context = context;
        this.coursePojoList = coursePojoList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.course_row, parent,false);
        return new CourseViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        holder.courseName.setText(coursePojoList.get(position).getCourseName());
        holder.courseDesp.setText(coursePojoList.get(position).getCourseDesc());
        holder.courseDuration.setText(" "+coursePojoList.get(position).getCourseDuration());
        holder.totalTime.setText(coursePojoList.get(position).getTotalHours());
        holder.courseCost.setText(String.valueOf(coursePojoList.get(position).getCourseCost()) );


        
        holder.Enrollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                view.startAnimation(shake);

                userAuthPreference = new UserAuthPreference(context);

                boolean status = userAuthPreference.getLoginStatus();

                if (status)
                {
                    Intent intent = new Intent(context,Enroll_List.class);
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context,LoginFrom.class);
                    context.startActivity(intent);

                }

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
        Button Enrollbtn;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.row_courseName);
            courseDesp = itemView.findViewById(R.id.row_courseDesp);
            courseDuration = itemView.findViewById(R.id.row_courseDuration);
            totalTime = itemView.findViewById(R.id.row_TotalTime);
            courseCost = itemView.findViewById(R.id.row_Cost);

            Enrollbtn = itemView.findViewById(R.id.enrollbtnID);


        }
    }




}
