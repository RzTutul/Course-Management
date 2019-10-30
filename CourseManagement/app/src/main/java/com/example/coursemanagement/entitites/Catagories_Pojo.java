package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "catagories_tbl")
public class Catagories_Pojo {
    @PrimaryKey(autoGenerate = true)
    private long catagories_id;
    private String catagories_name;

    @Ignore
    public Catagories_Pojo(long catagories_id, String catagories_name) {
        this.catagories_id = catagories_id;
        this.catagories_name = catagories_name;
    }

    public Catagories_Pojo(String catagories_name) {
        this.catagories_name = catagories_name;
    }

    public long getCatagories_id() {
        return catagories_id;
    }

    public void setCatagories_id(long catagories_id) {
        this.catagories_id = catagories_id;
    }

    public String getCatagories_name() {
        return catagories_name;
    }

    public void setCatagories_name(String catagories_name) {
        this.catagories_name = catagories_name;
    }

}
