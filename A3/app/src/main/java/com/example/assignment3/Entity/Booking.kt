package com.example.assignment3.Entity

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Booking (
    //TODO change appointment to booking
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "date") val bookingDate: String,
    @ColumnInfo(name = "time") val bookingTime: String,
    @ColumnInfo(name = "location") val bookingLocation: String,
    @ColumnInfo(name = "user") val bookingUser : String,
)