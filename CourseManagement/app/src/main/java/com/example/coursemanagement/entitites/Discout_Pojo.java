package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "discount_tbl")
public class Discout_Pojo {
    @PrimaryKey(autoGenerate = true)
    private int discount_id;
    private String c_id;

    @Ignore
    public Discout_Pojo(int discount_id, String c_id) {
        this.discount_id = discount_id;
        this.c_id = c_id;
    }

    public Discout_Pojo(String c_id) {
        this.c_id = c_id;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
