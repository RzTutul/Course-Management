package com.example.coursemanagement;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_information")
public class coursePojo{
    @PrimaryKey
    private int courseID;
    private String CourseName;
    private String CourseDesc;
    private String CourseDuration;
    private String TotalHours;
    private String CourseCatagories;
    private String CourseErollStatus;
    private int CourseCost;

    public coursePojo(int courseID, String CourseName, String CourseDesc, String CourseDuration, String TotalHours, String CourseCatagories, String CourseErollStatus, int CourseCost) {
        this.courseID = courseID;
        this.CourseName = CourseName;
        this.CourseDesc = CourseDesc;
        this.CourseDuration = CourseDuration;
        this.TotalHours = TotalHours;
        this.CourseCatagories = CourseCatagories;
        this.CourseErollStatus = CourseErollStatus;
        this.CourseCost = CourseCost;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseDesc() {
        return CourseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        CourseDesc = courseDesc;
    }

    public String getCourseDuration() {
        return CourseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        CourseDuration = courseDuration;
    }

    public String getTotalHours() {
        return TotalHours;
    }

    public void setTotalHours(String totalHours) {
        TotalHours = totalHours;
    }

    public String getCourseCatagories() {
        return CourseCatagories;
    }

    public void setCourseCatagories(String courseCatagories) {
        CourseCatagories = courseCatagories;
    }

    public String getCourseErollStatus() {
        return CourseErollStatus;
    }

    public void setCourseErollStatus(String courseErollStatus) {
        CourseErollStatus = courseErollStatus;
    }

    public int getCourseCost() {
        return CourseCost;
    }

    public void setCourseCost(int courseCost) {
        CourseCost = courseCost;
    }
}
