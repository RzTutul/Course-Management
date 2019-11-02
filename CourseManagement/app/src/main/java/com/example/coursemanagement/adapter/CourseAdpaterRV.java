package com.example.coursemanagement.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.shared_preference.UserAuthPreference;
import com.example.coursemanagement.activites.Enroll_List_activity;
import com.example.coursemanagement.activites.LoginFrom_activity;

import org.w3c.dom.Text;

import java.util.List;


public class CourseAdpaterRV extends RecyclerView.Adapter<CourseAdpaterRV.CourseViewHolder>{

    private Context context;
    private List<Course_Pojo> coursePojoList;
    private UserAuthPreference userAuthPreference;
    Bitmap bmp;

    public CourseAdpaterRV(Context context, List<Course_Pojo> coursePojoList) {
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
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, final int position) {


            holder.courseName.setText(coursePojoList.get(position).getCourseName());
            holder.courseDesp.setText(coursePojoList.get(position).getCourseDesc());
            holder.courseDuration.setText(" "+coursePojoList.get(position).getCourseDuration());
            holder.totalTime.setText(coursePojoList.get(position).getTotalHours());
            holder.courseCost.setText(String.valueOf(coursePojoList.get(position).getCourseCost()) );
               bmp = BitmapFactory.decodeFile(coursePojoList.get(position).getImage());
            holder.courseImage.setImageBitmap(bmp);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Course Details");
                    builder.setIcon(R.drawable.dateicon);
                    LayoutInflater inflater = LayoutInflater.from(context);

                    View view1 = inflater.inflate(R.layout.course_list_custom_dilog,null);

                    TextView CourseName = view1.findViewById(R.id.dialg_CourseNamTV);
                    ImageView CourseImage = view1.findViewById(R.id.dialog_courseImage);
                    CourseName.setText(coursePojoList.get(position).getCourseName());
                    Bitmap bitmap = BitmapFactory.decodeFile(coursePojoList.get(position).getImage());
                    CourseImage.setImageBitmap(bitmap);


                    final Button Enrollbtn = view1.findViewById(R.id.enrollbtn);
                    final Button WishListbtn = view1.findViewById(R.id.wishlistbtn);

                    builder.setView(view1);

                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    Enrollbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context, "Enrolled", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    WishListbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context, "Added WishListed", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });


                }





        });

        holder.Enrollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                view.startAnimation(shake);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Do you want enroll this course?");
                builder.setIcon(R.drawable.enrollicondilog);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userAuthPreference = new UserAuthPreference(context);
                        boolean status = userAuthPreference.getLoginStatus();

                        if (status)
                        {
                            Intent intent = new Intent(context, Enroll_List_activity.class);
                            context.startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(context, LoginFrom_activity.class);
                            context.startActivity(intent);

                        }
                    }
                });

                builder.setNegativeButton("No",null);

                AlertDialog dialog = builder.create();
                dialog.show();





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
        ImageView courseImage;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.row_courseName);
            courseDesp = itemView.findViewById(R.id.row_courseDesp);
            courseDuration = itemView.findViewById(R.id.row_courseDuration);
            totalTime = itemView.findViewById(R.id.row_TotalTime);
            courseCost = itemView.findViewById(R.id.row_Cost);

            Enrollbtn = itemView.findViewById(R.id.enrollbtnID);
            courseImage= itemView.findViewById(R.id.row_CourseImage);

        }
    }




}
