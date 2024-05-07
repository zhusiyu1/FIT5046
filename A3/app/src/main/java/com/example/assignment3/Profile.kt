package com.example.assignment3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Profile(modifier: Modifier = Modifier, navController: NavController?) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
    ) {
        Text(
            text = "Profile",
            color = Color(0xff1f41bb),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 70.dp
                ))
        Property1Active(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 34.dp,
                    y = 150.dp
                ))
        Property1Default1(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 36.dp,
                    y = 239.dp
                ))
        Tab(
            selected = false,
            onClick = {  },
            text = {
                Text(
                    text = "13/03/03",
                    color = Color(0xff1e232c).copy(alpha = 0.56f),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 329.dp
                )
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 50.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9))
                .border(
                    border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 20.dp))
        Tab(
            selected = false,
            onClick = {  },
            text = {
                Text(
                    text = "102 Rainforest Walk, Clayton, Australia, Victoria",
                    color = Color(0xff1e232c).copy(alpha = 0.56f),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 34.dp,
                    y = 419.dp
                )
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 62.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9))
                .border(
                    border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 20.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 34.dp,
                    y = 68.dp
                )
                .requiredSize(size = 41.dp)
        ) {
            BackButton(onClick = { GoBack(navController!!)}, Modifier)
        }
        Text(
            text = "Email",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 36.dp,
                    y = 126.dp
                ))
        Text(
            text = "Full Name",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 37.dp,
                    y = 216.dp
                )
                .requiredWidth(width = 84.dp)
        )
        Text(
            text = "Date of Birth",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 39.dp,
                    y = 305.dp
                ))
        Text(
            text = "Address",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 34.dp,
                    y = 395.dp
                ))
        Text(
            text = "Past Appointments",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 34.dp,
                    y = 497.dp
                )
                .requiredWidth(width = 158.dp)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 36.dp,
                    y = 519.dp
                )
                .requiredWidth(width = 325.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9)))
        Text(
            text = "16/01/2024   General Appointment",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 55.dp,
                    y = 544.dp
                )
                .requiredWidth(width = 273.dp)
        )
        Text(
            text = "30/02/2024 General Appointment",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 55.dp,
                    y = 594.dp
                )
                .requiredWidth(width = 272.dp)
        )
        Text(
            text = "13/04/2024  General Appointment",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 55.dp,
                    y = 643.dp
                )
                .requiredWidth(width = 273.dp)
        )
        Text(
            text = "13/04/2024  General Appointment",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 55.dp,
                    y = 693.dp
                )
                .requiredWidth(width = 273.dp)
        )
    }
}

@Composable
fun Property1Active(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 325.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xfff7f8f9))
            .border(
                border = BorderStroke(1.dp, Color(0xff1f41bb)),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(all = 20.dp)
    ) {
        Text(
            text = "example@gmail.com",
            color = Color(0xff1e232c).copy(alpha = 0.56f),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth())
    }
}

@Composable
fun Property1Default1(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 325.dp)
            .requiredHeight(height = 50.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xfff7f8f9))
            .border(
                border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(all = 20.dp)
    ) {
        Text(
            text = "Ibrahim Ibrahim",
            color = Color(0xff1e232c).copy(alpha = 0.56f),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth())
    }
}

fun GoBack(navController: NavController) {
    navController.navigate("Welcome")
}


@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .requiredSize(size = 41.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .border(
                border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back Arrow",
            tint = Color(0xff1e232c),
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview
@Composable
private fun ProfilePreview() {
    Profile(Modifier, null)
}
