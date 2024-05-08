package com.example.assignment3.Repository

import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.DAO.HospitalDao
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital
import kotlinx.coroutines.flow.Flow

class HealthBookingRepository(private val hospitalDao: HospitalDao, private val bookingDao: BookingDao) {

    val bookings: Flow<List<Booking>>  = bookingDao.getAll()
    val hospitals: Flow<List<Hospital>> = hospitalDao.getAll()

    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertAll(booking)
    }

    suspend fun insertHospital(hospital: Hospital) {
        hospitalDao.insertAll(hospital)
    }

    fun generate3Hospitals(): List<Hospital> {
        return listOf(
            Hospital(
                uid = 1,
                name = "Monash Campus Medical Center (Clayton)",
                address = "21 Chancellors Walk, Clayton VIC 3168",
            ),
            Hospital(
                uid = 2,
                name = "Monash Medical Center",
                address = "246 Clayton Rd, Clayton VIC 3168",

            ),
            Hospital(
                uid = 3,
                name = "Victorian Heart Hospital",
                address = "631 Blackburn Rd, Clayton VIC 3168"
            )
        )
    }


}