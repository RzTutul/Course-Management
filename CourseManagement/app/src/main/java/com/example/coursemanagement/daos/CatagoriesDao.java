package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.Categories_Pojo;
import com.example.coursemanagement.entitites.Course_Pojo;

import java.util.List;

@Dao
public interface CatagoriesDao {
    @Insert
    long addNewCatagories(Categories_Pojo categoriesPojo);

    @Query("select categories_name from categories_list_tbl")
    List<String> getCatagories();

    @Query("select categories_id from categories_list_tbl where categories_name like:cata")
    long getCatagoriesID(String cata);
}
