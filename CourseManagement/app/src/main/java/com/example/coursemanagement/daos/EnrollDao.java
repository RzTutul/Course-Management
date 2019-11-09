package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.Enroll_list_Pojo;
import com.example.coursemanagement.joinquerymodel.StudentWithEnrollCourse;

import java.util.List;

@Dao
public interface EnrollDao {
    @Insert
    long AddEntroll(Enroll_list_Pojo enrollListPojo);


    @Query("select * from enroll_list_tbl")
    List<Enroll_list_Pojo> getAllEnrolllist();


  @Query("select * from enroll_list_tbl inner join student_info_tbl on " +
          "enroll_list_tbl.student_id = student_info_tbl.std_id " +
          "inner join course_information_tbl " +
          "on course_information_tbl.courseID =enroll_list_tbl.c_id")
    List<StudentWithEnrollCourse> getAllstudentWithEnroll();


    @Query("select * from enroll_list_tbl inner join student_info_tbl on " +
            "enroll_list_tbl.student_id = student_info_tbl.std_id " +
            "inner join course_information_tbl " +
            "on course_information_tbl.courseID =enroll_list_tbl.c_id where enroll_list_tbl.student_id=:id")
    List<StudentWithEnrollCourse> getEnrollforSingleUSer(long id);


    @Query("select * from enroll_list_tbl inner join student_info_tbl on " +
            "enroll_list_tbl.student_id = student_info_tbl.std_id " +
            "inner join course_information_tbl " +
            "on course_information_tbl.courseID = enroll_list_tbl.c_id where course_information_tbl.courseName=:courseName")
    List<StudentWithEnrollCourse> getEnrollforSingleUSerFilter(String courseName);


    @Query("select COUNT(eid) from enroll_list_tbl")
    long getTotalEnrollStudent();


    @Query("select COUNT(eid) from enroll_list_tbl inner join student_info_tbl on " +
            "enroll_list_tbl.student_id = student_info_tbl.std_id " +
            "inner join course_information_tbl " +
            "on course_information_tbl.courseID = enroll_list_tbl.c_id where course_information_tbl.courseName=:CourseName")
    long getTotalEnrollStudentbyCourse(String CourseName);








}
