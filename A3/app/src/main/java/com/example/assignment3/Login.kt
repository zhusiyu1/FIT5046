package com.example.assignment3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun Login(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Login",
            color = Color.Blue,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 150.dp)
                .align(Alignment.TopCenter)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 270.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )
        Button(
            onClick = { },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 300.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text("Google Account")
        }
        Button(
            onClick = { navController.navigate("Home") },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 200.dp)
                .align(Alignment.BottomCenter)
                .width(200.dp),
        ) {
            Text("Login")
        }
        Button(
            onClick = { },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 150.dp)
                .align(Alignment.BottomCenter)
                .width(200.dp),
        ) {
            Text("Register")
        }

    }

}