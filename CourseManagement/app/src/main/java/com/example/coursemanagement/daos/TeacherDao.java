package com.example.coursemanagement.daos;

import androidx.room.Dao;

import com.example.coursemanagement.entitites.Teacher_Pojo;

@Dao
public interface TeacherDao {
    long addNewTeacher(Teacher_Pojo teacherPojo);
}
