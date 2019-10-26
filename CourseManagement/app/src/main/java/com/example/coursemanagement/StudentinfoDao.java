package com.example.coursemanagement;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentinfoDao {
    @Insert
    long InsertNewStudent (StudentInfoPojo studentpojo);

    @Query("Select * from 'Student_info'")
  List<StudentInfoPojo> getAllStudentInfo();


}
