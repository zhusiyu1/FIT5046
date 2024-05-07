package com.example.assignment3.HealthViewModel

import android.provider.ContactsContract.Profile
import androidx.lifecycle.ViewModel
import com.example.assignment3.State.ProfileUiState
import java.util.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class HealthViewModel: ViewModel() {

    // UI State
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    // Business logic

    // Get user information from the store
    private lateinit var profileInformation: Array<Any>

    fun getUserInformation(): Array<Any> {
        profileInformation = arrayOf(profileUiState.value.email, profileUiState.value.fullName, profileUiState.value.username, profileUiState.value.phone, profileUiState.value.address)
        return profileInformation
    }

    // Create booking function.
    // @params: Booking date, User Id, Doctor, Clinic Location
    // This will take in booking date as timestamp? user id for identification,\
    // doctor id for identification and clinic location id.
    fun scheduleBooking(date: Any, userId: String, doctorId: Any, clinicIid: Any) {
        return
    }
}