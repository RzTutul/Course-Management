package com.example.coursemanagement.entitites;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "messagebox_tbl")
public class MessageBox_Pojo {
    @PrimaryKey(autoGenerate = true)
   private long id;
   private String messageText;

   @Ignore
    public MessageBox_Pojo(long id, String messageText) {
        this.id = id;
        this.messageText = messageText;
    }

    public MessageBox_Pojo(String messageText) {
        this.messageText = messageText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
