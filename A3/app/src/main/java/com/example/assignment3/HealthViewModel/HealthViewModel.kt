package com.example.assignment3.HealthViewModel

import androidx.annotation.WorkerThread
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


@HiltViewModel
class HealthViewModel @Inject constructor(private val healthBookingRepository: HealthBookingRepository) :
    ViewModel() {

    init {
        viewModelScope.launch {
            getUserInfo()
        }
        val auth = FirebaseAuth.getInstance()
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            _authState.value = firebaseAuth.currentUser
        }
        auth.addAuthStateListener(authStateListener)

        // Start listening for authentication state changes
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener)

    }

    // ****************************************
    //              UI State
    // ****************************************

    // Profile
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    // Booking
    private val _bookingUiState = MutableStateFlow(BookingUiState())
    val bookingUiState: StateFlow<BookingUiState> = _bookingUiState.asStateFlow()

    // User
    private val _userUiState = MutableStateFlow(User())
    val userUiState: StateFlow<User> = _userUiState.asStateFlow()

    var username: String = profileUiState.value.username
    var address: String = profileUiState.value.address

    private val _authState = MutableStateFlow<FirebaseUser?>(null)
    val authState: StateFlow<FirebaseUser?> = _authState

    // DB
    val bookings: LiveData<List<Booking>> = healthBookingRepository.bookings.asLiveData()
    val hospitals: LiveData<List<Hospital>> = healthBookingRepository.hospitals.asLiveData()

    // ****************************************
    //             Business logic
    // ****************************************

    suspend fun getUserAllBookings() {
        viewModelScope.launch {
            val user = Firebase.auth.currentUser
            if (user != null) {
                val bookings = healthBookingRepository.getUserBookings(user.uid)
                return@launch bookings
            } else {
                return@launch
            }
        }
    }

    // Update user profile
    fun updateUserProfile(updatedUser: User, updatedUsername: String, updatedAddress: String) {
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            // https://firebase.google.com/docs/reference/kotlin/com/google/firebase/auth/UserProfileChangeRequest
            // Can only update username
            displayName = updatedUsername
        }
        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("User profile updated.")
                }


                // Get user object
                // {firstName=sad, lastName=sad, password=123456789, gender=m, mobilePhone=123, dateOfBirth=14/05/2023, email=123456789@qq.com}
                val database = FirebaseDatabase.getInstance()
                database.reference.child("users").child(user!!.email!!.replace(".", "_"))
                    .setValue(updatedUser).addOnSuccessListener {
                        println("Successfully updated user")
                    }
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

    fun getUserInfo() {
        val user = Firebase.auth.currentUser

        viewModelScope.launch {
            val database = FirebaseDatabase.getInstance()
            try {
                database.reference.child("users").child(user!!.email!!.replace(".", "_")).get()
                    .addOnSuccessListener {
                        _userUiState.value = it.getValue(User::class.java)!!
//                        _userUiState.value = it.getValue(User::class.java)!!
                    }.addOnFailureListener() {
                        println("No user found")
                    }
            } catch (e: Exception) {
                println("No user found")
            }

        }
    }

    fun resetUserUiState() {
        _userUiState.value = User()
    }

}

