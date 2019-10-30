package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.coursemanagement.entitites.Discout_Pojo;

@Dao
public interface DiscountDao {
    @Insert
    long addDiscount(Discout_Pojo discoutPojo);
}
