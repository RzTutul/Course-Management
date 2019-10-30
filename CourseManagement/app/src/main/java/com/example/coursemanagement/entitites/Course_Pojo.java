package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_information")
public class Course_Pojo {
    @PrimaryKey(autoGenerate = true)
    private long courseID;
    private String courseName;
    private String courseDesc;
    private String image;
    private String courseDuration;
    private String totalHours;
    private String courseCatagories;
    private String courseErollStatus;
    private long courseCost;


    @Ignore
    public Course_Pojo(long courseID, String courseName, String courseDesc, String image, String courseDuration, String totalHours, String courseCatagories, String courseErollStatus, long courseCost) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.image = image;
        this.courseDuration = courseDuration;
        this.totalHours = totalHours;
        this.courseCatagories = courseCatagories;
        this.courseErollStatus = courseErollStatus;
        this.courseCost = courseCost;
    }

    public Course_Pojo(String courseName, String courseDesc, String image, String courseDuration, String totalHours, String courseCatagories, String courseErollStatus, long courseCost) {
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.image = image;
        this.courseDuration = courseDuration;
        this.totalHours = totalHours;
        this.courseCatagories = courseCatagories;
        this.courseErollStatus = courseErollStatus;
        this.courseCost = courseCost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getCourseCatagories() {
        return courseCatagories;
    }

    public void setCourseCatagories(String courseCatagories) {
        this.courseCatagories = courseCatagories;
    }

    public String getCourseErollStatus() {
        return courseErollStatus;
    }

    public void setCourseErollStatus(String courseErollStatus) {
        this.courseErollStatus = courseErollStatus;
    }

    public long getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(long courseCost) {
        this.courseCost = courseCost;
    }
}