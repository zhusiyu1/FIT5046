package com.example.assignment3

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.hilt.navigation.compose.hiltViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun Home(navController: NavController, healthViewModel: HealthViewModel = hiltViewModel()) {

    val bookings = healthViewModel.bookings.observeAsState().value

    val formatter = SimpleDateFormat("dd MM yyyy", Locale.ROOT)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "Home",
                color = Color.Blue,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                "Upcoming Appointments",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Display appointments
            if (bookings != null) {
                LazyColumn {
                    items(bookings) { booking ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(bottom = 4.dp)) {
                            Card(
                                modifier = Modifier.border(
                                    1.dp,
                                    Color.Gray,
                                    shape = RoundedCornerShape(16.dp)
                                )
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Appointment ${booking.uid}")
                                    Text("Date: ${formatter.format(Date(booking.bookingDate))} at ${booking.bookingTime}")
                                    Text("Clinic: ${booking.bookingLocationName}")
                                    Text("Address: ${booking.bookingLocationAddress}")
                                }
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "No Appointment",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                )
            }
        }
    }
}
