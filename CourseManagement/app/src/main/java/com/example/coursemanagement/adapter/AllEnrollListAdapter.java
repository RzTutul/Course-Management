package com.example.coursemanagement.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.coursemanagement.db.CourseDatebase;
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
    public void onBindViewHolder(@NonNull AllEnrollViewHolder holder, final int position) {

        holder.std_name.setText(studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_name());
        holder.std_phone.setText(studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_phone());
        holder.std_email.setText(studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_email());
        holder.courseName.setText(studentWithEnrollCourseList.get(position).coursePojo.getCourseName());

        holder.callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri phoneUri = Uri.parse("tel:"+studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_phone());
                Intent callItent = new Intent(Intent.ACTION_CALL, phoneUri);
                context.startActivity(callItent);

            }
        });
           holder.messagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = CourseDatebase.getInstance(context).getMessageBoxDao().getMessage();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_phone()));  // This ensures only SMS apps respond
                intent.putExtra("sms_body", msg);
                //intent.putExtra(Intent.EXTRA_STREAM, attachment);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, "no component found", Toast.LENGTH_SHORT).show();
                }
            }
        });
           holder.emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String sub= CourseDatebase.getInstance(context).getEmailDao().getEmailSubject();
                String mailText= CourseDatebase.getInstance(context).getEmailDao().getComposeEmail();




                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + studentWithEnrollCourseList.get(position).studentInfoPojo.getStd_email()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, sub);
                    intent.putExtra(Intent.EXTRA_TEXT, mailText);
                    context.startActivity(intent);
                }
                catch(ActivityNotFoundException e){
                    //TODO smth
                }
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
