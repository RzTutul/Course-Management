package com.example.coursemanagement.daos;

import androidx.room.Dao;

import com.example.coursemanagement.entitites.WishList_pojo;

@Dao
public interface WishListDao {
    long AddWishList(WishList_pojo wishListPojo);
}
