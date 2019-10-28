package com.example.coursemanagement;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    long InsertNewCourse(coursePojo coursePojo);
    @Update
    int UpdateCourse(coursePojo coursePojo);
    @Delete
    int DeleteCourse(coursePojo coursePojo);

    @Query("select * from course_information order by courseID desc")
    List<coursePojo> getAllCourse();

    @Query("Select * from course_information where courseID like:id")
    coursePojo getCourseID(int id);

}
