package com.example.assignment3.HealthViewModel

import androidx.annotation.WorkerThread
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asLiveData
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.room.Room
import androidx.room.util.copy
import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.Database.HealthBookerRoomDatabase
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital
import com.example.assignment3.Repository.HealthBookingRepository
import com.example.assignment3.State.BookingUiState
import com.example.assignment3.State.ProfileUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.assignment3.MainActivity
import com.example.assignment3.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HealthViewModel @Inject constructor(private val healthBookingRepository: HealthBookingRepository) : ViewModel() {

//    init {
//        viewModelScope.launch {
//            healthBookingRepository.generate3Hospitals()
//        }
//    }

    // ****************************************
    //              UI State
    // ****************************************

    // Profile
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    // Booking
    private val _bookingUiState =  MutableStateFlow(BookingUiState())
    val bookingUiState: StateFlow<BookingUiState> = _bookingUiState.asStateFlow()

    // User
    private val _userUiState = MutableStateFlow(User())
    val userUiState: StateFlow<User> = _userUiState.asStateFlow()

    var username: String = profileUiState.value.username
    var fullName: String = profileUiState.value.fullName
    var address: String = profileUiState.value.address

    // DB
    val bookings: LiveData<List<Booking>> = healthBookingRepository.bookings.asLiveData()
    val hospitals: LiveData<List<Hospital>> = healthBookingRepository.hospitals.asLiveData()


    // ****************************************
    //             Business logic
    // ****************************************

    // Update user profile
    fun updateUserProfile() {
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            // https://firebase.google.com/docs/reference/kotlin/com/google/firebase/auth/UserProfileChangeRequest
            // Can only update username
            displayName = username
        }

        val updatedUser = User(
            email = _userUiState.value.email,
            firstName = _userUiState.value.firstName,
            lastName = _userUiState.value.lastName,
            dateOfBirth = _userUiState.value.dateOfBirth,
            mobilePhone = _userUiState.value.mobilePhone,
            password = _userUiState.value.password,
            gender = _userUiState.value.gender,
        )

        // Get user object
        // {firstName=sad, lastName=sad, password=123456789, gender=m, mobilePhone=123, dateOfBirth=14/05/2023, email=123456789@qq.com}
        val database = FirebaseDatabase.getInstance()
        database.reference.child("users").child(user!!.email!!.replace(".", "_")).setValue(updatedUser).addOnSuccessListener {
            println("Successfully updated user")
        }
    }

    // Create booking function.
    @WorkerThread
    fun scheduleBooking(booking: Booking) = viewModelScope.launch {
        println("Schedule Booking ${booking}")
        healthBookingRepository.insertBooking(booking)
    }

    // Select hospital
    fun selectHospital(hospital: Hospital) = viewModelScope.launch {
        _bookingUiState.value.hospital = hospital
    }

    // Select Booking
//    fun selectBooking(booking: Booking) = viewModelScope.launch {
//        _bookingUiState.value.booking = booking
//    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val repository = (this[APPLICATION_KEY] as MainActivity).repository
//                HealthViewModel(
//                    healthBookingRepository = repository,
//                )
//            }
//        }
//    }

}

//class HealthViewModelFactory(private val healthBookingRepository: HealthBookingRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HealthViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return HealthViewModel(healthBookingRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
