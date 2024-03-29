package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories_list_tbl")
public class Categories_Pojo {
    @PrimaryKey(autoGenerate = true)
    private long categories_id;
    private String categories_name;

    public Categories_Pojo(String categories_name) {
        this.categories_name = categories_name;
    }

    @Ignore
    public Categories_Pojo(long categories_id, String categories_name) {
        this.categories_id = categories_id;
        this.categories_name = categories_name;
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
}
