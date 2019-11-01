package com.example.coursemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;

import org.w3c.dom.Text;

import java.util.List;

public  abstract class EnrollAdapterRV extends RecyclerView.Adapter<EnrollAdapterRV.EnrollViewHolder>{

    private Context context;
    private List<Enroll_list_Pojo> enrollListPojos;

    public EnrollAdapterRV(Context context, List<Enroll_list_Pojo> enrollListPojos) {
        this.context = context;
        this.enrollListPojos = enrollListPojos;
    }

    @NonNull
    @Override
    public EnrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.enroll_row,parent,false);

        return new EnrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnrollViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return enrollListPojos.size();
    }

    public class EnrollViewHolder extends RecyclerView.ViewHolder
    {

        TextView courseName,CourseDescription,duration,totalTime,cost;

        public EnrollViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.row_courseName);
            CourseDescription = itemView.findViewById(R.id.row_courseDesp);
            duration = itemView.findViewById(R.id.row_courseDuration);
            totalTime = itemView.findViewById(R.id.row_TotalTime);
            cost = itemView.findViewById(R.id.row_Cost);



        }
    }
}
