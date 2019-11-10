package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.Teacher_Pojo;

import java.util.List;

@Dao
public interface TeacherDao {

    @Insert
    long addNewTeacher(Teacher_Pojo teacherPojo);

    @Query("Select * from Teacher_tbl")
    List<Teacher_Pojo> getAllTeacher();
}
