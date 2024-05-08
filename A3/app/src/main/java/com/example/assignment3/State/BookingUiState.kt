package com.example.assignment3.State

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital

data class BookingUiState (
    val uid: Int? = null,
    var hospital: Hospital? = null,
    val bookingDate: Long? = null,
    val bookingTime: String? = null,
    val bookingLocationId: Int? = null,
    val bookingLocationName: String? = null,
    val bookingLocationAddress: String? = null,
    val bookingUser : String? = null,
)