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
fun PreviousAppoinments(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 852.dp)
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
    ) {
        Text(
            text = "History   ",
            color = Color(0xff1f41bb),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp,
                    y = 70.dp))
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
                    .fillMaxSize())
        }
        Text(
            text = "6 months ago",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 33.dp,
                    y = 121.dp)
                .requiredWidth(width = 158.dp)
                .requiredHeight(height = 18.dp))
        Text(
            text = "Past 12 months",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 33.dp,
                    y = 590.dp)
                .requiredWidth(width = 158.dp)
                .requiredHeight(height = 18.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 148.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 92.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 251.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 92.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 466.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 92.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 358.dp)
                .requiredWidth(width = 322.dp)
                .requiredHeight(height = 92.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff7f8f9)))
        Text(
            text = "16/01/2024   General Appointment.\nDoctor Sam\n$500",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 53.dp,
                    y = 164.dp)
                .requiredWidth(width = 287.dp))
        Text(
            text = "30/02/2024 General Appointment\nDoctor Mark\n$150",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 53.dp,
                    y = 265.dp)
                .requiredWidth(width = 287.dp)
                .requiredHeight(height = 64.dp))
        Text(
            text = "13/04/2024  General Appointment\nDoctor Sam\n$300",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 49.dp,
                    y = 374.dp))
        Text(
            text = "13/04/2024  General Appointment\nDoctor Sam\n$475",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 49.dp,
                    y = 482.dp))
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun PreviousAppoinmentsPreview() {
    PreviousAppoinments(Modifier)
}