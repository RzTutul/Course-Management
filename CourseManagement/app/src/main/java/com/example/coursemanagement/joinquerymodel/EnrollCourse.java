package com.example.coursemanagement.joinquerymodel;

import androidx.room.Embedded;

import com.example.coursemanagement.entitites.Course_Pojo;
import com.example.coursemanagement.entitites.Enroll_list_Pojo;

public class EnrollCourse {

    @Embedded
    Course_Pojo course_pojo;
    @Embedded
    Enroll_list_Pojo enrollListPojo;
}
