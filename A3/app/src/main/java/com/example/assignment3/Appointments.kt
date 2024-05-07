package com.example.assignment3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
@Composable
fun Appointment(modifier: Modifier = Modifier, navController: NavController?) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
    ) {
        Text(
            text = "Book an Appointment",
            color = Color(0xff1f41bb),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.5.dp,
                    y = 119.dp))
        Property1Default(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp,
                    y = 194.dp))
        Tab(
            selected = false,
            onClick = {  },
            text = {
                Text(
                    text = "55 Rainforest walk, Clayton",
                    color = Color(0xff1e232c),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 290.dp)
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 50.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9))
                .border(border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                    shape = RoundedCornerShape(10.dp))
                .padding(all = 20.dp))
        Tab(
            selected = false,
            onClick = {  },
            text = {
                Text(
                    text = "Dr Adam May",
                    color = Color(0xff1e232c),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 378.dp)
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 50.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9))
                .border(border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                    shape = RoundedCornerShape(10.dp))
                .padding(all = 20.dp))
        Tab(
            selected = false,
            onClick = {  },
            text = {
                Text(
                    text = "$300",
                    color = Color(0xff1e232c).copy(alpha = 0.56f),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 467.dp)
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 50.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9))
                .border(border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                    shape = RoundedCornerShape(10.dp))
                .padding(all = 20.dp))
        Text(
            text = "Doctor",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 35.dp,
                    y = 354.dp))
        Tab(
            selected = false,
            onClick = {  },
            text = {
                Text(
                    text = "Book Now",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 18.sp))
            },
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 4.dp,
                    y = 556.dp)
                .requiredWidth(width = 325.dp)
                .requiredHeight(height = 50.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xff1f41bb))
                .padding(horizontal = 20.dp,
                    vertical = 15.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 68.dp)
                .requiredSize(size = 41.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 41.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = Color.White)
                    .border(border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                        shape = RoundedCornerShape(12.dp)))
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "back_arrow",
                colorFilter = ColorFilter.tint(Color(0xff1e232c)),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = "Date",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 38.dp,
                    y = 170.dp))
        Text(
            text = "Location",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 36.dp,
                    y = 266.dp))
        Text(
            text = "Total Cost",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 447.dp))
    }
}

@Composable
fun Property1Default(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 325.dp)
            .requiredHeight(height = 50.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xfff7f8f9))
            .border(border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                shape = RoundedCornerShape(10.dp))
            .padding(all = 20.dp)
    ) {
        Text(
            text = "07/02/2024 5:30PM",
            color = Color(0xff1e232c),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth())
    }
}

fun PreviousPage(navController: NavController) {
    navController.navigate("Welcome")
}


@Composable
fun GoBackButton(
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
private fun AppointmentPreview() {
    Appointment(Modifier, null)
}