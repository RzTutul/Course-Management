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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.R;
import com.example.coursemanagement.db.CourseDatebase;
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;
import com.example.coursemanagement.shared_preference.UserAuthPreference;
import com.example.coursemanagement.user_activites.Enroll_List_activity;
import com.example.coursemanagement.user_activites.LoginFrom_activity;
import com.example.coursemanagement.shared_preference.UserIdPreference;

import java.util.ArrayList;
import java.util.List;


public class CourseAdpaterRV extends RecyclerView.Adapter<CourseAdpaterRV.CourseViewHolder> implements
        Filterable {

    private Context context;
    private List<Course_Pojo> coursePojoList;
    private List<Course_Pojo> courseFilter;

    private UserAuthPreference userAuthPreference;
    private UserIdPreference userIdPreference;

    Bitmap bmp;

    public CourseAdpaterRV(Context context, List<Course_Pojo> coursePojoList) {
        this.context = context;
        this.coursePojoList = coursePojoList;
        this.courseFilter = coursePojoList;


    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.course_row, parent,false);
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.layout_animation);
        view.startAnimation(shake);
            return new CourseViewHolder(view) ;


    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, final int position) {


            holder.courseName.setText(courseFilter.get(position).getCourseName());
            holder.courseDesp.setText(courseFilter.get(position).getCourseDesc());
            holder.courseDuration.setText(" "+courseFilter.get(position).getCourseDuration());
            holder.totalTime.setText(courseFilter.get(position).getTotalHours());
            holder.courseCost.setText(String.valueOf(courseFilter.get(position).getCourseCost()) );
               bmp = BitmapFactory.decodeFile(courseFilter.get(position).getImage());
            holder.courseImage.setImageBitmap(bmp);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
                view.startAnimation(shake);

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Course Details");
                    builder.setIcon(R.drawable.dateicon);
                    LayoutInflater inflater = LayoutInflater.from(context);

                    View view1 = inflater.inflate(R.layout.course_list_custom_dilog,null);

                    TextView CourseName = view1.findViewById(R.id.dialg_CourseNamTV);
                    TextView CourseDesc = view1.findViewById(R.id.dialg_CourseDescTV);
                    TextView CourseDuration = view1.findViewById(R.id.dialg_CourseDurationID);
                    TextView CourseTotalTIme = view1.findViewById(R.id.dialg_CourseTotalTime);
                    TextView CourseCost = view1.findViewById(R.id.dialg_CourseCost);



                    ImageView CourseImage = view1.findViewById(R.id.dialog_courseImage);
                    CourseName.setText(courseFilter.get(position).getCourseName());
                    Bitmap bitmap = BitmapFactory.decodeFile(courseFilter.get(position).getImage());
                    CourseImage.setImageBitmap(bitmap);
                CourseDesc.setText(courseFilter.get(position).getCourseDesc());
                CourseDuration.setText(courseFilter.get(position).getCourseDuration());
                CourseTotalTIme.setText(courseFilter.get(position).getTotalHours());
                CourseCost.setText(String.valueOf(courseFilter.get(position).getCourseCost()));





                    final Button Enrollbtn = view1.findViewById(R.id.enrollbtn);
                    final Button WishListbtn = view1.findViewById(R.id.wishlistbtn);

                    builder.setView(view1);
                    final AlertDialog dialog = builder.create();
                    dialog.show();

                    Enrollbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

                            builder1.setTitle("Do you want enroll this course?");
                            builder1.setIcon(R.drawable.enrollicondilog);


                            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    userAuthPreference = new UserAuthPreference(context);
                                    boolean status = userAuthPreference.getLoginStatus();
                                    userIdPreference = new UserIdPreference(context);

                                    if (status)
                                    {
                                        Course_Pojo coursePojo = courseFilter.get(position);
                                        String c_id = coursePojo.getCourseID();
                                        long s_id = userIdPreference.getLoginID();

                                        Enroll_list_Pojo enrollListPojo = new Enroll_list_Pojo(c_id,s_id);
                                        long insertEnrol = CourseDatebase.getInstance(context).getEnrollDao().AddEntroll(enrollListPojo);
                                        if (insertEnrol>0)
                                        {
                                            Toast.makeText(context, "Enrolled", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(context, Enroll_List_activity.class);
                                            context.startActivity(intent);
                                        }
                                    }
                                    else
                                    {
                                        Intent intent = new Intent(context, LoginFrom_activity.class);
                                        context.startActivity(intent);

                                    }
                                }
                            });

                            builder1.setNegativeButton("No",null);

                            AlertDialog dialog = builder1.create();
                            dialog.show();





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
                        userIdPreference = new UserIdPreference(context);

                        if (status)
                        {
                            Course_Pojo coursePojo = courseFilter.get(position);
                            String c_id = coursePojo.getCourseID();
                            long s_id = userIdPreference.getLoginID();

                            Enroll_list_Pojo enrollListPojo = new Enroll_list_Pojo(c_id,s_id);
                            long insertEnrol = CourseDatebase.getInstance(context).getEnrollDao().AddEntroll(enrollListPojo);
                            if (insertEnrol>0)
                            {
                                Toast.makeText(context, "Enrolled", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, Enroll_List_activity.class);
                                context.startActivity(intent);
                            }
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
        return courseFilter.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String query = constraint.toString();
                if (query.isEmpty()){
                    courseFilter = coursePojoList;
                }else{
                    List<Course_Pojo> tempList = new ArrayList<>();
                    for (Course_Pojo m : coursePojoList){
                        if (m.getCourseName().toLowerCase().contains(query) /*||
                                String.valueOf(m.getReleaseYear()).contains(query) ||
                                m.getCategory().toLowerCase().contains(query)*/){
                            tempList.add(m);
                        }
                    }
                    courseFilter = tempList;
                }

                FilterResults results = new FilterResults();
                results.values = courseFilter;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                courseFilter= (List<Course_Pojo>) results.values;
                notifyDataSetChanged();
            }
        };
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
