package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.EmailBox_Pojo;

@Dao
public interface EmailBoxDao {
    @Insert
    long InsertNewEmail(EmailBox_Pojo emailBox_pojo);


    @Query("Select email_subject from emailBox_tbl order by e_id desc LIMIT 1")
    String getEmailSubject();

    @Query("Select composeEmail from emailBox_tbl order by e_id desc LIMIT 1")
    String getComposeEmail();





}
