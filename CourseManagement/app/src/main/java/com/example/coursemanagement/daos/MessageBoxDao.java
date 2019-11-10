package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.MessageBox_Pojo;

import java.util.List;

@Dao
public interface MessageBoxDao {

    @Insert
    long AddNewMessage(MessageBox_Pojo messageBox_pojo);

    @Query("Select messageText from messagebox_tbl order by id desc LIMIT 1")
    String getMessage();
}
