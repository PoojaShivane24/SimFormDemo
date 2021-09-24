package com.example.simformdemo.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable

@Entity(tableName = "Users")
data class Results(val gender : String, @Embedded val name : Name, @Embedded val location : Location, @PrimaryKey val email : String, @Embedded val dob : DOB, val phone : String, @Embedded val picture : Picture) : Serializable






