package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Enroll_list")
public class Enroll_list_Pojo {
    @PrimaryKey(autoGenerate = true)
    private int eid;
    private String c_id;
    private int std_id;

    @Ignore
    public Enroll_list_Pojo(int e_id, String c_id, int std_id) {
        eid = e_id;
        this.c_id = c_id;
        this.std_id = std_id;
    }

    public Enroll_list_Pojo(String c_id, int std_id) {
        this.c_id = c_id;
        this.std_id = std_id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public int getStd_id() {
        return std_id;
    }

    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }
}
