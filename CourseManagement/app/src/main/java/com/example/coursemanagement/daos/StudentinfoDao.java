package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.StudentInfo_Pojo;
import com.example.coursemanagement.joinquerymodel.studentWithEnrollCourse;

import java.util.List;

@Dao
public interface StudentinfoDao {
    @Insert
    long InsertNewStudent(StudentInfo_Pojo studentpojo);

    @Query("Select * from Student_info")
  List<StudentInfo_Pojo> getAllStudentInfo();

    @Query("select 'std_id' from student_info where 'std_email' like : email and 'std_password' like : pass")
    long  getId(String email,String pass);



}
