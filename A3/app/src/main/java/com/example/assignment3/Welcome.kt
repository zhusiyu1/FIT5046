package com.example.assignment3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Welcome(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "HealthBooker",
            color = Color.Blue,
            textAlign = TextAlign.Left,
            style = TextStyle(
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.hospital),
            contentDescription = "Hospital",
            modifier = Modifier.size(350.dp)
                .padding(top = 150.dp)
        )
        Button(
            onClick = { navController.navigate("Login") },
            modifier = Modifier
                .padding(bottom = 300.dp)
                .align(Alignment.BottomCenter)
                .width(300.dp),
        ) {
            Text(
                "Login",
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .padding(bottom = 220.dp)
                .align(Alignment.BottomCenter)
                .width(300.dp),
        ) {
            Text(
                "Register",
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
        }
    }
}