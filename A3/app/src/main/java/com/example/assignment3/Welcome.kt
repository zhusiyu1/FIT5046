package com.example.assignment3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
        GoToProfile(onClick = { NavigateToProfile(navController) }, Modifier)
    }
}

fun NavigateToProfile(navController: NavController){
    navController.navigate("Profile")
}

@Composable
fun GoToProfile(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .border(
                border = BorderStroke(1.dp, Color(0xffe8ecf4)),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = "Go to Profile",
            modifier = Modifier.padding(8.dp)
        )
    }
}
