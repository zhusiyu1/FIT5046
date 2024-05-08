package com.example.assignment3.Database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.DAO.HospitalDao
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KParameter

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
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

//private class HealthBookerDatabaseCallback(
//    private val scope: CoroutineScope
//) : RoomDatabase.Callback() {
//
//    override fun onCreate(db: SupportSQLiteDatabase) {
//        super.onCreate(db)
//        KParameter.Kind.INSTANCE?.let { database ->
//            scope.launch {
//                populateDatabase(database.hospitalDao())
//            }
//        }
//    }
//
//    suspend fun populateDatabase(hospitalDao: HospitalDao) {
//        // Delete all content here.
////        hospitalDao.deleteAll()
//
//        // Add sample words.
//        var hospital1 =
//            Hospital(
//                uid = 1,
//                name = "Monash Campus Medical Center (Clayton)",
//                address = "21 Chancellors Walk, Clayton VIC 3168",
//            )
//
//        val hospital2 =
//            Hospital(
//                uid = 2,
//                name = "Monash Medical Center",
//                address = "246 Clayton Rd, Clayton VIC 3168",
//
//                )
//        val hospital3 =
//            Hospital(
//                uid = 3,
//                name = "Victorian Heart Hospital",
//                address = "631 Blackburn Rd, Clayton VIC 3168"
//            )
//
//        hospitalDao.insertAll(hospital1)
//        hospitalDao.insertAll(hospital2)
//        hospitalDao.insertAll(hospital3)
//
//        // TODO: Add your own words!
//    }
//}