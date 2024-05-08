package com.example.assignment3.State

import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital

data class BookingUiState (
    var hospital: Hospital? = null,
    var booking: Booking? = null,
)