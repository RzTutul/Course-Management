package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.Categories_Pojo;

import java.util.List;

@Dao
public interface CatagoriesDao {
    @Insert
    long addNewCatagories(Categories_Pojo categoriesPojo);

    @Query("select categories_name from 'categories_list'")
    List<String> getCatagories();
}
