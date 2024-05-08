package com.example.assignment3

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.ui.theme.Assignment3Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mobilePhone by remember { mutableStateOf("") }
    var auth = Firebase.auth

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button
        IconButton(onClick = { navController.navigate("Welcome")}, modifier = Modifier.align(Alignment.Start)) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        // Title
        Text(text = "Register", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // First Name
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Last Name
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date of Birth
        DateOfBirthField(
            dateOfBirth = dateOfBirth,
            onDateSelected = { newDate -> dateOfBirth = newDate }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Gender
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mobile Phone
        OutlinedTextField(
            value = mobilePhone,
            onValueChange = { mobilePhone = it },
            label = { Text("Mobile Phone") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Register Button
        Button(
            onClick = {
                val user = User(
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    dateOfBirth = dateOfBirth,
                    gender = gender,
                    password = password,
                    mobilePhone = mobilePhone
                )


                storeUserInDatabase(user)
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        }
                    }
                navController.navigate("Login")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }
    }
}

//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateOfBirthField(dateOfBirth: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()

    if (showDatePicker) {
        android.app.DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                val newDate = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(
                    GregorianCalendar(year, month, day).time
                )
                onDateSelected(newDate)
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    OutlinedTextField(
        value = dateOfBirth,
        onValueChange = { },
        readOnly = true,
        label = { Text("Date of Birth") },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { showDatePicker = true }) {
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Select Date")
            }
        }
    )
}

fun storeUserInDatabase(user: User) {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.reference.child("users")
    val emailKey = user.email.replace(".", "_")
    usersRef.child(emailKey).setValue(user)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignment3Theme {
        RegisterPage(navController = rememberNavController())
    }
}

