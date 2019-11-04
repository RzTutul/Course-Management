package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories_list")
public class Categories_Pojo {
    @PrimaryKey(autoGenerate = true)
    private long categories_id;
    private String course_name;
    private String categories_name;

    @Ignore
    public Categories_Pojo(long categories_id, String course_name, String categories_name) {
        this.categories_id = categories_id;
        this.course_name = course_name;
        this.categories_name = categories_name;
    }

    public Categories_Pojo(String course_name, String categories_name) {
        this.course_name = course_name;
        this.categories_name = categories_name;
    }

    public Categories_Pojo(String catagories_name) {
        this.categories_name = catagories_name;
    }

    public long getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(long categories_id) {
        this.categories_id = categories_id;
    }

    public String getCategories_name() {
        return categories_name;
    }

    public void setCategories_name(String categories_name) {
        this.categories_name = categories_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
