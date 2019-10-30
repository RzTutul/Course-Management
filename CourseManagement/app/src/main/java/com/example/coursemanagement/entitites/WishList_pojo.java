package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "WishList_tbl")
public class WishList_pojo {
    @PrimaryKey(autoGenerate = true)
    private long w_id;
    private String c_id;
    private int s_id;

    @Ignore
    public WishList_pojo(long w_id, String c_id, int s_id) {
        this.w_id = w_id;
        this.c_id = c_id;
        this.s_id = s_id;
    }

    public WishList_pojo(String c_id, int s_id) {
        this.c_id = c_id;
        this.s_id = s_id;
    }

    public long getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

}
