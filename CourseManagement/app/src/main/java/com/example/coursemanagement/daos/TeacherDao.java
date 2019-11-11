package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.coursemanagement.entitites.Teacher_Pojo;

import java.util.List;

@Dao
public interface TeacherDao {

    @Insert
    long addNewTeacher(Teacher_Pojo teacherPojo);

    @Delete
    int deleteTeacher(Teacher_Pojo teacher_pojo);

 @Update
    int updateTeacher(Teacher_Pojo teacher_pojo);


    @Query("Select * from Teacher_tbl where t_id like:id")
    Teacher_Pojo getTeacherID(long id);

    @Query("Select * from Teacher_tbl")
    List<Teacher_Pojo> getAllTeacher();
}
