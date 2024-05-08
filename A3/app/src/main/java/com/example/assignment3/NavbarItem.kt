package com.example.assignment3

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem (
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    fun NavBarItems(): List<NavBarItem> {
        return listOf(
            NavBarItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Routes.Home.value
            ),
            NavBarItem(
                label = "Profile",
                icon = Icons.Filled.Person,
                route = Routes.Profile.value
            ),
            NavBarItem(
                label = "Map",
                icon = Icons.Filled.Place,
                route = Routes.Map.value
            ),
            NavBarItem(
                label = "Booking",
                icon = Icons.Filled.DateRange,
                route = Routes.Appointment.value
            ),

        )
    }
}