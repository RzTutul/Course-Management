package com.example.coursemanagement.adapter;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.example.coursemanagement.admin_activity.Add_Teacher_Activity;
import com.example.coursemanagement.admin_activity.All_TeacherList_Activity;
import com.example.coursemanagement.db.CourseDatebase;
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
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.layout_animation);
        view.startAnimation(shake);

        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder,final int position) {


        holder.tname.setText(teacher_pojos.get(position).getT_name());
        holder.tphone.setText(teacher_pojos.get(position).getT_phone());
        holder.temail.setText(teacher_pojos.get(position).getT_email());

        Bitmap bitmap = BitmapFactory.decodeFile(teacher_pojos.get(position).getImage());
        holder.tImage.setImageBitmap(bitmap);
        holder.tphone.setText(teacher_pojos.get(position).getT_phone());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.teacherlist_dilog,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Modifty?");
                builder.setIcon(R.drawable.details);

                Button edit = view.findViewById(R.id.teditbtn);
                Button delete = view.findViewById(R.id.tdeletebtn);

                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.show();

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Teacher_Pojo teacherPojo = teacher_pojos.get(position);
                        long id = teacherPojo.getT_id();

                        Intent intent = new Intent(context, Add_Teacher_Activity.class);
                        intent.putExtra("id",id);
                        context.startActivity(intent);

                    }
                });

                   delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Teacher_Pojo teacherPojo = teacher_pojos.get(position);
                        CourseDatebase.getInstance(context).getTeacherDao().deleteTeacher(teacherPojo);

                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        Intent intent = new Intent(context, All_TeacherList_Activity.class);
                     context.startActivity(intent);



                    }
                });


                return false;
            }
        });

        holder.callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri phoneUri = Uri.parse("tel:"+teacher_pojos.get(position).getT_phone());
                Intent callItent = new Intent(Intent.ACTION_CALL, phoneUri);
                context.startActivity(callItent);
            }
        });
           holder.messagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + teacher_pojos.get(position).getT_email()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
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
        return teacher_pojos.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {

        TextView tname,tphone,temail;
        Button callbtn,messagebtn;
        ImageView tImage;
        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            tname = itemView.findViewById(R.id.teacherNameID);
            tphone = itemView.findViewById(R.id.teacherPhoneID);
            temail = itemView.findViewById(R.id.teacherEmailID);
            callbtn = itemView.findViewById(R.id.t_callbtn);
            messagebtn = itemView.findViewById(R.id.t_messagebtn);
            tImage = itemView.findViewById(R.id.teacherImageID);


        }
    }
}
