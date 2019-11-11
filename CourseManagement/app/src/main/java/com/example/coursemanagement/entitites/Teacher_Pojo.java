package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "Teacher_tbl")
public class Teacher_Pojo {
    @PrimaryKey(autoGenerate = true)
    private long t_id;
    private String t_name;
    private String t_phone;
    private String t_email;
    private String image;


    @Ignore
    public Teacher_Pojo(long t_id, String t_name, String t_phone, String t_email, String image) {
        this.t_id = t_id;
        this.t_name = t_name;
        this.t_phone = t_phone;
        this.t_email = t_email;
        this.image = image;
    }

    public Teacher_Pojo(String t_name, String t_phone, String t_email, String image) {
        this.t_name = t_name;
        this.t_phone = t_phone;
        this.t_email = t_email;
        this.image = image;
    }

    public long getT_id() {
        return t_id;
    }

    public void setT_id(long t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_phone() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone = t_phone;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
