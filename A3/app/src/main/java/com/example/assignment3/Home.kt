package com.example.assignment3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Composable
fun Home(navController: NavController, name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = "Home",
            color = Color.Blue,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 70.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = name,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 25.sp,
            ),
            modifier = Modifier
                .padding(top = 150.dp)
        )
        Text(
            text = "Your Appointments",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 300.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "No Appointment",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
            ),
            modifier = Modifier
                .padding(top = 400.dp)
                .align(Alignment.TopCenter)
        )

    }
}
//test
//test1
//test2