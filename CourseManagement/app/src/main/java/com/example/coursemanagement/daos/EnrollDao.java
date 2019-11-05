package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.Enroll_list_Pojo;

import java.util.List;

@Dao
public interface EnrollDao {
    @Insert
    long AddEntroll(Enroll_list_Pojo enrollListPojo);

/*    @Query("select * from 'Enroll_list'")
    List<String> getAllValue();*/
}
