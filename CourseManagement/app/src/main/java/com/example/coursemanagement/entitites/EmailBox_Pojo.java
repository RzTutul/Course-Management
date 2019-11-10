package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "emailBox_tbl")
public class EmailBox_Pojo{
    @PrimaryKey(autoGenerate = true)
    private long e_id;
    private String email_subject;
    private String composeEmail;

    @Ignore
    public EmailBox_Pojo(long e_id, String email_subject, String composeEmail) {
        this.e_id = e_id;
        this.email_subject = email_subject;
        this.composeEmail = composeEmail;
    }

    public EmailBox_Pojo(String email_subject, String composeEmail) {
        this.email_subject = email_subject;
        this.composeEmail = composeEmail;
    }

    public long getE_id() {
        return e_id;
    }

    public void setE_id(long e_id) {
        this.e_id = e_id;
    }

    public String getEmail_subject() {
        return email_subject;
    }

    public void setEmail_subject(String email_subject) {
        this.email_subject = email_subject;
    }

    public String getComposeEmail() {
        return composeEmail;
    }

    public void setComposeEmail(String composeEmail) {
        this.composeEmail = composeEmail;
    }
}
