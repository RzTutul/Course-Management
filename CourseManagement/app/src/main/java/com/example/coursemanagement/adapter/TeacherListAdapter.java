package com.example.coursemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.entitites.Teacher_Pojo;

import java.util.List;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.TeacherViewHolder> {

    private Context context;
    private List<Teacher_Pojo>teacher_pojos;

    public TeacherListAdapter(Context context, List<Teacher_Pojo> teacher_pojos) {
        this.context = context;
        this.teacher_pojos = teacher_pojos;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.teacher_row,parent,false);


        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {


        holder.tName.setText(teacher_pojos.get(position).getT_name());
        holder.tphone.setText(teacher_pojos.get(position).getT_phone());

    }

    @Override
    public int getItemCount() {
        return teacher_pojos.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {

        TextView tName,tphone;
        Button callbtn,messagebtn;
        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            tName = itemView.findViewById(R.id.row_teacherName);
            tphone = itemView.findViewById(R.id.row_teacherPhone);
        }
    }
}
