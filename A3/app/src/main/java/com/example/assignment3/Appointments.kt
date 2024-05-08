package com.example.assignment3

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment3.Database.HealthBookerRoomDatabase
import com.example.assignment3.Entity.Hospital
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.example.assignment3.Repository.HealthBookingRepository
import java.util.Locale

@Composable
fun Appointment( modifier: Modifier = Modifier, navController: NavController?, healthViewModel: HealthViewModel) {


    val hospitals = healthViewModel.hospitals.observeAsState().value
//    println("Hospital Values: ${hospitals}")

    var edit by remember { mutableStateOf(true) }
    var expanded by remember {
        mutableStateOf(
            false
        )
    }

    val expand: () -> Unit = {
        expanded = true
    }

    val selectAHospital: (hospital: Hospital) -> Unit = { hospital ->
        healthViewModel.selectHospital(hospital)
        expanded = false
    }

//    IconButton(onClick = { expanded = true }) {
//        Icon(
//            imageVector = Icons.Filled.ArrowDropDown,
//            contentDescription = "Toggle hospital list"
//        )
//    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "Book an Appointment",
            color = Color(0xff1f41bb),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Column(modifier = Modifier.padding(bottom=16.dp)) {
            Text(
                text = "Date",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )
            Property1Active(Modifier, "Date", { }, edit)
        }
        Column(modifier = Modifier.padding(bottom=16.dp)) {
            Text(
                text = "Hospital",
                color = Color(0xff1e232c),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Box(modifier = Modifier.clickable { expanded = true }) {
                Property1Default1(Modifier)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                hospitals?.forEach { hospital ->
                    DropdownMenuItem(onClick = { selectAHospital(hospital) }, text = {Text("${hospital.name}")})
                }
//                DropdownMenuItem(onClick = {  }, text = {Text("Drop Down 1")})
//                DropdownMenuItem(onClick = {  }, text = {Text("Drop Down 2")})
//                DropdownMenuItem(onClick = {  }, text = {Text("Drop Down 3")})
            }
        }
        Column(modifier = Modifier.padding(bottom=16.dp)) {
            Text(
                text = "Location",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )
            Property1Active(Modifier, "Location", { }, edit)
        }
        Button(onClick = {}) {
            Text(text = "Book Now")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(modifier: Modifier = Modifier) {
    val locale: Locale = Locale("Australia")

    DatePicker(state = DatePickerState(locale))
}

@Composable
fun Property1Default1(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 325.dp)
            .background(color = Color(0xfff7f8f9))
            .border(
                border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(all = 20.dp)
    ) {
        Text(
            text = "Monash Campus Medical Center (Clayton)",
            color = Color(0xff1e232c).copy(alpha = 0.56f),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth())
    }
}

@Preview
@Composable
private fun AppointmentPreview() {
    Appointment( Modifier, null, viewModel())
}