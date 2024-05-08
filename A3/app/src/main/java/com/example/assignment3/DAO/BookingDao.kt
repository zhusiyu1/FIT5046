package com.example.assignment3.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment3.Entity.Booking
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Query("SELECT * FROM booking")
    fun getAll(): Flow<List<Booking>>

    @Insert
    fun insertAll(vararg bookings: Booking)

}