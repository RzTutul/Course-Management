package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SingleUserEnroll&Wishlist")
public class Single_UserEnroll_wishList_Pojo {

    @PrimaryKey
    private long std_id ;
    private String courseName;
    private String courseDes;
    private String courseDuration;
    private String courseTotalTime;
    private String courseCost;

    public Single_UserEnroll_wishList_Pojo(long std_id, String courseName, String courseDes, String courseDuration, String courseTotalTime, String courseCost) {
        this.std_id = std_id;
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.courseDuration = courseDuration;
        this.courseTotalTime = courseTotalTime;
        this.courseCost = courseCost;
    }

    public long getStd_id() {
        return std_id;
    }

    public void setStd_id(long std_id) {
        this.std_id = std_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseTotalTime() {
        return courseTotalTime;
    }

    public void setCourseTotalTime(String courseTotalTime) {
        this.courseTotalTime = courseTotalTime;
    }

    public String getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(String courseCost) {
        this.courseCost = courseCost;
    }
}
