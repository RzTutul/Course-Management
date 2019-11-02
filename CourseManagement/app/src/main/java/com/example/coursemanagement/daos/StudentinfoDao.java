package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.StudentInfo_Pojo;

import java.util.List;

@Dao
public interface StudentinfoDao {
    @Insert
    long InsertNewStudent(StudentInfo_Pojo studentpojo);

    @Query("Select * from Student_info")
  List<StudentInfo_Pojo> getAllStudentInfo();


}
