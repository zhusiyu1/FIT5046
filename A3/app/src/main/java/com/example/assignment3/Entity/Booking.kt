package com.example.assignment3.Entity

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Booking (
    //TODO change appointment to booking
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "date") val bookingDate: Long,
    @ColumnInfo(name = "time") val bookingTime: String,
    @ColumnInfo(name = "location_id") val bookingLocationId: Int,
    @ColumnInfo(name = "location_name") val bookingLocationName: String,
    @ColumnInfo(name = "location_address") val bookingLocationAddress: String,
    @ColumnInfo(name = "user") val bookingUser : String,
)