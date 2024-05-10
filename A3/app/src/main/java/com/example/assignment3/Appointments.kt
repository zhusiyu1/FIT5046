package com.example.assignment3

import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberDatePickerState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment3.Entity.Booking
import com.example.assignment3.Entity.Hospital
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Appointment(
    modifier: Modifier = Modifier,
    navController: NavController?,
    healthViewModel: HealthViewModel
) {


    val context: Context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val hospitals = healthViewModel.hospitals.observeAsState().value

    val bookingUiState = healthViewModel.bookingUiState.collectAsState().value

    // Now (Time and Date)
    val timeNow = LocalTime.now()
    val dateNow = Instant.now().toEpochMilli()

    var selectedDate by remember { mutableStateOf<Long>(dateNow) }
    var selectedTime by remember { mutableStateOf(timeNow.toString()) }
    var selectedHospital by remember {
        mutableStateOf<Hospital?>(hospitals?.get(0))
    }

    var edit by remember { mutableStateOf(true) }
    var expanded by remember {
        mutableStateOf(
            false
        )
    }

    val selectAHospital: (hospital: Hospital) -> Unit = { hospital ->
        selectedHospital = hospital
        healthViewModel.selectHospital(hospital)
        expanded = false
    }

    // https://stackoverflow.com/questions/73859164/how-do-i-make-a-disabled-compose-outlinedtextfield-look-like-its-enabled
    // How do I make a disabled compose OutlinedTextField look like its enabled? (n.d.). Stack Overflow. Retrieved May 10, 2024, from https://stackoverflow.com/questions/73859164/how-do-i-make-a-disabled-compose-outlinedtextfield-look-like-its-enabled

    val enabledColors = OutlinedTextFieldDefaults.colors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = Color.Transparent,
        disabledBorderColor = MaterialTheme.colorScheme.outline,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    val book: () -> Unit = {
        if (auth.currentUser != null) {
            if (selectedDate!! < dateNow && selectedTime < timeNow.toString()) {
                Toast.makeText(context, "Please select a future date and time", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (selectedHospital != null) {
                    healthViewModel.scheduleBooking(
                        Booking(
                            bookingDate = selectedDate!!,
                            bookingTime = selectedTime,
                            bookingLocationId = selectedHospital!!.uid,
                            bookingLocationName = selectedHospital!!.name,
                            bookingLocationAddress = selectedHospital!!.address,
                            bookingUser = auth.currentUser!!.uid,
                        )
                    )
                    print("Auth user ${FirebaseAuth.getInstance().currentUser!!.uid}")
                    print("User ${FirebaseAuth.getInstance().currentUser!!}")

                    navController?.navigate("Home")
                } else {
                    Toast.makeText(context, "Please select a hospital", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    // Validations


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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column() {
                Text(
                    text = "Date",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
            Column(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DatePickerComponent({ date ->
                    selectedDate = date
                }, enabledColors)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Time",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.fillMaxWidth(0.8f)

            )
            Column(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TimePickerComponent({ time ->
                    selectedTime = time
                }, enabledColors)
            }
        }

        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            Text(
                text = "Hospital",
                color = Color(0xff1e232c),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Box(modifier = Modifier.clickable { expanded = true }) {
                if (selectedHospital != null) {
                    Property1Default1(
                        Modifier,
                        selectedHospital!!.name,
                        enabledColors = enabledColors
                    )
                } else {
                    Property1Default1(
                        Modifier,
                        "Monash Campus Medical Center (Clayton)",
                        enabledColors = enabledColors
                    )
                }

            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.requiredWidth(300.dp)
            ) {
                hospitals?.forEach { hospital ->
                    DropdownMenuItem(
                        onClick = { selectAHospital(hospital) },
                        text = { Text("${hospital.name}") })
                }
            }
        }
        Button(onClick = {
            book()
        }) {
            Text(text = "Book Now")
        }
    }
}

@Composable
fun Property1Default1(
    modifier: Modifier = Modifier,
    selectedHospitalName: String,
    enabledColors: TextFieldColors
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth(0.8f)
    ) {
        OutlinedTextField(
            value = selectedHospitalName,
            onValueChange = { Unit },
            colors = enabledColors,
            enabled = false,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(onDateSelected: (Long) -> Unit, enabledColors: TextFieldColors) {
    val calendar = Calendar.getInstance()
    calendar.set(2024, 0, 1) // month (0) is January
    calendar.set(LocalDate.now().year, LocalDate.now().monthValue - 1, LocalDate.now().dayOfMonth)

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli(),
    )
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }

    val formatter = SimpleDateFormat("d/MM/Y", Locale.ROOT)
    val formattedDate = formatter.format(Date(selectedDate))

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                        selectedDate = datePickerState.selectedDateMillis ?: calendar.timeInMillis
                        onDateSelected(Date(selectedDate).time)
                    }) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                    }) {
                        Text(text = "Cancel")
                    }
                },

                ) {
                DatePicker(state = datePickerState)
            }
        }
    }

    OutlinedTextField(
        value = formattedDate,
        onValueChange = {},
        readOnly = true,
        modifier = Modifier
            .clickable { showDatePicker = true }
            .fillMaxWidth(0.8f),
        enabled = false,
        label = { "Select Date" },
        colors = enabledColors

    )
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun TimePickerComponent(onTimeSelected: (String) -> Unit, enabledColors: TextFieldColors) {
    val calendar = Calendar.getInstance()
    calendar.set(2024, 0, 1) // month (0) is January

    val now = LocalTime.now()
    var selectedHour by remember { mutableStateOf(now.hour.toString()) }
    var selectedMinute by remember { mutableStateOf(now.minute.toString()) }

    var showTimePicker by remember {
        mutableStateOf(false)
    }

    val cal = Calendar.getInstance()
    val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        selectedHour = hour.toString()
        selectedMinute = minute.toString()
        showTimePicker = false
        onTimeSelected("$hour:$minute")
    }


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (showTimePicker) {
            TimePickerDialog(
                LocalContext.current,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true,
            ).show()
        }

        OutlinedTextField(
            value = "${selectedHour}: ${selectedMinute}",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .clickable { showTimePicker = true }
                .fillMaxWidth(0.8f),
            label = { Text("Select Time") },
            enabled = false,
            colors = enabledColors
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun AppointmentPreview() {
    Appointment(Modifier, null, viewModel())
}