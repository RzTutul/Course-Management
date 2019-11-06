package com.example.coursemanagement.joinquerymodel;

import androidx.room.Embedded;

import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;
import com.example.coursemanagement.entitites.StudentInfo_Pojo;

import java.security.PublicKey;

public class StudentWithEnrollCourse {

    @Embedded
    public Enroll_list_Pojo enrollListPojo;

    @Embedded
    public StudentInfo_Pojo studentInfoPojo;
    @Embedded
    public Course_Pojo coursePojo;
}
