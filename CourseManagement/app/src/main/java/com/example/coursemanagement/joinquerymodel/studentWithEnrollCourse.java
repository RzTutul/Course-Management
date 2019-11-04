package com.example.coursemanagement.joinquerymodel;

import androidx.room.Embedded;

import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.entitites.StudentInfo_Pojo;

import java.security.PublicKey;

public class studentWithEnrollCourse {

    @Embedded
    public StudentInfo_Pojo studentInfoPojo;
    @Embedded
    public Course_Pojo coursePojo;
}
