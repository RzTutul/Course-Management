package com.example.coursemanagement;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {coursePojo.class},version = 1)
public abstract class CourseDatebase extends RoomDatabase {

   public abstract CourseDao getCourseDao();
   private static CourseDatebase db;


   public static CourseDatebase getInstance(Context context)
   {
       if (db == null) {
           db = Room.databaseBuilder(context, CourseDatebase.class, "Course_DB").allowMainThreadQueries().build();

           return db;
       }
       return db;
       }



}
