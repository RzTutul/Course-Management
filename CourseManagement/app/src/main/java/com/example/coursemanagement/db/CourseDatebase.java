package com.example.coursemanagement.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.coursemanagement.daos.CatagoriesDao;
import com.example.coursemanagement.daos.CourseDao;
import com.example.coursemanagement.daos.EmailBoxDao;
import com.example.coursemanagement.daos.EnrollDao;
import com.example.coursemanagement.daos.MessageBoxDao;
import com.example.coursemanagement.daos.SingleUserEnrollDao;
import com.example.coursemanagement.daos.StudentinfoDao;
import com.example.coursemanagement.daos.TeacherDao;
import com.example.coursemanagement.entitites.Categories_Pojo;
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.entitites.EmailBox_Pojo;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;
import com.example.coursemanagement.entitites.MessageBox_Pojo;
import com.example.coursemanagement.entitites.StudentInfo_Pojo;
import com.example.coursemanagement.entitites.Teacher_Pojo;

@Database(entities = {Course_Pojo.class, StudentInfo_Pojo.class, Teacher_Pojo.class, Enroll_list_Pojo.class,  Categories_Pojo.class, MessageBox_Pojo.class, EmailBox_Pojo.class},version = 1)

public abstract class CourseDatebase extends RoomDatabase {

   public abstract CourseDao getCourseDao();
   public abstract StudentinfoDao getStudentDao();
   public abstract SingleUserEnrollDao getSinguserDao();
   public abstract TeacherDao getTeacherDao();
   public abstract EnrollDao getEnrollDao();
   public  abstract CatagoriesDao getCatagoriesDao();
   public  abstract MessageBoxDao getMessageBoxDao();
   public abstract EmailBoxDao getEmailDao();
   private static CourseDatebase db;
  /* private static Migration MIGRATION_1_2 = new Migration(1,2) {
       @Override
       public void migrate(@NonNull SupportSQLiteDatabase database) {
           database.execSQL("create table 'Student_info'('std_id' integer primary key not null, 'std_name' text, 'std_email' text, 'std_phone' text, 'std_password' text ) ");
       }
   };
*/
   public static CourseDatebase getInstance(Context context)
   {
       if (db == null) {
           db = Room.databaseBuilder(context, CourseDatebase.class, "Course_DB").allowMainThreadQueries()/*.addMigrations(MIGRATION_1_2)*/.build();

           return db;
       }
       return db;
       }



}
