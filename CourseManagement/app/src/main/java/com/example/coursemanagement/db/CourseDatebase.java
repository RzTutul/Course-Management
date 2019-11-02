package com.example.coursemanagement.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.coursemanagement.daos.CourseDao;
import com.example.coursemanagement.daos.StudentinfoDao;
import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.entitites.Discout_Pojo;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;
import com.example.coursemanagement.entitites.StudentInfo_Pojo;
import com.example.coursemanagement.entitites.Teacher_Pojo;
import com.example.coursemanagement.entitites.WishList_pojo;

@Database(entities = {Course_Pojo.class, Discout_Pojo.class, StudentInfo_Pojo.class, Teacher_Pojo.class, Enroll_list_Pojo.class, WishList_pojo.class},version = 1)

public abstract class CourseDatebase extends RoomDatabase {

   public abstract CourseDao getCourseDao();
   public abstract StudentinfoDao getStudentDao();
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
