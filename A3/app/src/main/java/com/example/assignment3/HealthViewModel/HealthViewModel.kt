package com.example.assignment3.HealthViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.Database.HealthBookerRoomDatabase
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital
import com.example.assignment3.Repository.HealthBookingRepository
import com.example.assignment3.State.ProfileUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HealthViewModel(private val healthBookingRepository: HealthBookingRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            healthBookingRepository.generate3Hospitals()
        }
    }

    // ****************************************
    //              UI State
    // ****************************************

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    var email: String = profileUiState.value.email
    var username: String = profileUiState.value.username
    var fullName: String = profileUiState.value.fullName
    var dob: String = profileUiState.value.dob
    var phone: String = profileUiState.value.phone
    var address: String = profileUiState.value.address

    // DB
    val bookings: LiveData<List<Booking>> = healthBookingRepository.bookings.asLiveData()
    val hospitals: LiveData<List<Hospital>> = healthBookingRepository.hospitals.asLiveData()


    // ****************************************
    //             Business logic
    // ****************************************

    // Update user profile
    fun updateUserProfile() {
        println("${email}, ${username}: ${fullName} , ${fullName}, ${dob} , ${phone} ,$address}")
    }

    // Create booking function.
    fun scheduleBooking(booking: Booking) = viewModelScope.launch {
        healthBookingRepository.insertBooking(booking)
    }
}