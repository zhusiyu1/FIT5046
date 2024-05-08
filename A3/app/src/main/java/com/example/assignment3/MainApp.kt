package com.example.assignment3

import android.app.Application
import com.example.assignment3.Database.HealthBookerRoomDatabase
import com.example.assignment3.Repository.HealthBookingRepository
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApp: Application() {
    val db by lazy {HealthBookerRoomDatabase.getDatabase(applicationContext)}
    val repository by lazy {HealthBookingRepository(db.hospitalDao(), db.bookingDao())}

//    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
//    val repository by lazy { WordRepository(database.wordDao()) }

}