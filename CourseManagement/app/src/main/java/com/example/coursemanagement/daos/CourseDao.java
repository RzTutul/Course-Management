package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coursemanagement.entitites.Course_Pojo;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    long InsertNewCourse(Course_Pojo coursePojo);
    @Update
    int UpdateCourse(Course_Pojo coursePojo);
    @Delete
    int DeleteCourse(Course_Pojo coursePojo);

    @Query("select * from course_information order by courseID desc")
    List<Course_Pojo> getAllCourse();

    @Query("Select * from course_information where courseID like:id")
    Course_Pojo getCourseID(int id);

}
