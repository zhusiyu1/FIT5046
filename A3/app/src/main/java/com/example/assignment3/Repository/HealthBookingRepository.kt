package com.example.assignment3.Repository

import androidx.annotation.WorkerThread
import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.DAO.HospitalDao
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class HealthBookingRepository @Inject constructor(private val hospitalDao: HospitalDao, private val bookingDao: BookingDao) {

    // Get only user bookings
    val bookings: Flow<List<Booking>> = bookingDao.getAll()
    val hospitals: Flow<List<Hospital>> = hospitalDao.getAll()


    @WorkerThread
    suspend fun getUserBookings(user: String) {
        bookingDao.getById(user)
    }

    @WorkerThread
    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertAll(booking)
    }

    @WorkerThread
    suspend fun insertHospital(hospital: Hospital) {
        hospitalDao.insertAll(hospital)
    }

    suspend fun deleteHospitalAll(){
        hospitalDao.deleteAll()
    }

    @WorkerThread
    suspend fun generate3Hospitals(): Unit {

        deleteHospitalAll()

        var hospital1 =
            Hospital(
                uid = 1,
                name = "Monash Campus Medical Center (Clayton)",
                address = "21 Chancellors Walk, Clayton VIC 3168",
            )

        val hospital2 =
            Hospital(
                uid = 2,
                name = "Monash Medical Center",
                address = "246 Clayton Rd, Clayton VIC 3168",

                )
        val hospital3 =
            Hospital(
                uid = 3,
                name = "Victorian Heart Hospital",
                address = "631 Blackburn Rd, Clayton VIC 3168"
            )

        insertHospital(hospital1)
        insertHospital(hospital2)
        insertHospital(hospital3)
//        println("Hospitals generated")
    }


}