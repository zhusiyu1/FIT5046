package com.example.assignment3.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hospital (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "hospital_name") val name: String,
    @ColumnInfo(name = "hospital_address") val address: String
)