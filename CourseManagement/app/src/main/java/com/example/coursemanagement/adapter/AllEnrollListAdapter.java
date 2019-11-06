package com.example.coursemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.joinquerymodel.StudentWithEnrollCourse;

import java.util.List;


public class AllEnrollListAdapter extends RecyclerView.Adapter<AllEnrollListAdapter.AllEnrollViewHolder>{
private Context context;
private List<StudentWithEnrollCourse> studentWithEnrollCourseList;

    public AllEnrollListAdapter(Context context, List<StudentWithEnrollCourse> studentWithEnrollCourseList) {
        this.context = context;
        this.studentWithEnrollCourseList = studentWithEnrollCourseList;
    }

    @NonNull
    @Override
    public AllEnrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.all_enroll_student_row,parent,false);
        return new AllEnrollListAdapter.AllEnrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllEnrollViewHolder holder, int position) {

        holder.std_name.setText(studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_name());
        holder.std_phone.setText(studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_phone());
        holder.std_email.setText(studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_email());
        holder.courseName.setText(studentWithEnrollCourseList.get(position).coursePojo.getCourseName());

        holder.callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "call", Toast.LENGTH_SHORT).show();
            }
        });
           holder.messagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "message", Toast.LENGTH_SHORT).show();
            }
        });
           holder.emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "call", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentWithEnrollCourseList.size();
    }

    public class AllEnrollViewHolder extends RecyclerView.ViewHolder
    {
        TextView std_name,std_phone,std_email,courseName;
        ImageView stdImage;
        Button callbtn,messagebtn,emailbtn;

        public AllEnrollViewHolder(@NonNull View itemView) {
            super(itemView);

            std_name = itemView.findViewById(R.id.e_nameTV);
            std_email = itemView.findViewById(R.id.e_emailTV);
            std_phone = itemView.findViewById(R.id.e_phoneNumberTV);
            courseName = itemView.findViewById(R.id.e_courseNameTV);
            callbtn = itemView.findViewById(R.id.e_callbtn);
            messagebtn = itemView.findViewById(R.id.e_messagebtn);
            emailbtn = itemView.findViewById(R.id.e_emailbtn);

        }
    }
}
