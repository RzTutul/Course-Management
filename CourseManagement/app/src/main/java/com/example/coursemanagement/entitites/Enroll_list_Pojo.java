package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Enroll_list",foreignKeys  ={@ForeignKey(entity = Course_Pojo.class,
        parentColumns = "courseID",
        childColumns = "c_id",
        onDelete = CASCADE,
        onUpdate = CASCADE),
        @ForeignKey(entity = StudentInfo_Pojo.class,
        parentColumns = "std_id",
        childColumns = "student_id",
        onDelete = CASCADE,
        onUpdate = CASCADE)})
public class Enroll_list_Pojo {
    @PrimaryKey(autoGenerate = true)
    private long eid;
    private String c_id;
    private long student_id;

    @Ignore
    public Enroll_list_Pojo(long eid, String c_id, long student_id) {
        this.eid = eid;
        this.c_id = c_id;
        this.student_id = student_id;
    }


    public Enroll_list_Pojo(String c_id, long student_id) {
        this.c_id = c_id;
        this.student_id = student_id;
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }
}
