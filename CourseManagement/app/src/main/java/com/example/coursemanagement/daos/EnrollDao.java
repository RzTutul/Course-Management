package com.example.coursemanagement.daos;

import androidx.room.Dao;

import com.example.coursemanagement.entitites.Enroll_list_Pojo;

@Dao
public interface EnrollDao {
    long AddEntroll(Enroll_list_Pojo enrollListPojo);
}
