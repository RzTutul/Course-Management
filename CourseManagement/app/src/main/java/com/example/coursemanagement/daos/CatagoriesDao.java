package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CatagoriesDao {
    @Insert
    long addNewCatagories(CatagoriesDao catagoriesDao);
}
