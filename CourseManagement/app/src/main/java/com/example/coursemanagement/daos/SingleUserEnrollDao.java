package com.example.coursemanagement.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursemanagement.entitites.Single_UserEnroll_wishList_Pojo;

import java.util.List;

@Dao
public interface SingleUserEnrollDao {
  /*  @Insert
   long insertNewSingleUserEnrollList( Single_UserEnroll_wishList_Pojo singleUserPojo);
*/
   /* @Query("Select * from 'SingleUserEnroll&Wishlist' where std_id like:id")
    Single_UserEnroll_wishList_Pojo getID(long id);

    @Query("Select * from 'SingleUserEnroll&Wishlist' where std_id like:id")
    List<Single_UserEnroll_wishList_Pojo> getAllValues(long id);*/

}
