package com.example.assignment3.DI

import android.content.Context
import androidx.room.Room
import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.DAO.HospitalDao
import com.example.assignment3.Database.HealthBookerRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides fun provideHealthBookerRoomDatabase(@ApplicationContext context: Context): HealthBookerRoomDatabase {
        return Room.databaseBuilder(context, HealthBookerRoomDatabase::class.java, "health_booker_database").build()
    }

    @Provides fun provideBookingDao(healthBookerRoomDatabase: HealthBookerRoomDatabase): BookingDao {
        return healthBookerRoomDatabase.bookingDao()
    }

    @Provides fun provideHospitalDao(healthBookerRoomDatabase: HealthBookerRoomDatabase): HospitalDao {
        return healthBookerRoomDatabase.hospitalDao()
    }
}