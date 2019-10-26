package com.example.coursemanagement;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {coursePojo.class,StudentInfoPojo.class},version = 2)
public abstract class CourseDatebase extends RoomDatabase {

   public abstract CourseDao getCourseDao();
   private static CourseDatebase db;
   private static Migration MIGRATION_1_2 = new Migration(1,2) {
       @Override
       public void migrate(@NonNull SupportSQLiteDatabase database) {
           database.execSQL("create table 'Student_info'('std_id' integer primary key not null, 'std_name' text, 'std_email' text, 'std_phone' text, 'std_password' text ) ");
       }
   };

   public static CourseDatebase getInstance(Context context)
   {
       if (db == null) {
           db = Room.databaseBuilder(context, CourseDatebase.class, "Course_DB").allowMainThreadQueries().addMigrations(MIGRATION_1_2).build();

           return db;
       }
       return db;
       }



}
