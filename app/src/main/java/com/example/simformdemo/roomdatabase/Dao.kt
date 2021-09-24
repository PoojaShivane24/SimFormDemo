package com.example.simformdemo.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simformdemo.model.Results

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: Results) : Long

//    @Query("Select * from UserEntity where userName = :userName AND password = :password")
//    fun getUser(userName : String, password : String) : UserEntity?

    @Query("Select * from Users")
    fun getUserList() : List<Results>
}