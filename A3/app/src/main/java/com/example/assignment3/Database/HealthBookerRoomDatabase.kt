package com.example.assignment3.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.DAO.HospitalDao
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital

@Database(entities = [Hospital::class, Booking::class], version = 1)
abstract class HealthBookerRoomDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
    abstract fun hospitalDao(): HospitalDao

    companion object {
        @Volatile
        private var INSTANCE: HealthBookerRoomDatabase? = null

        fun getDatabase(context: Context): HealthBookerRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthBookerRoomDatabase::class.java,
                    "health_booker_database"
                )
                    .setQueryExecutor()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}