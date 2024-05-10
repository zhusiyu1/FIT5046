package com.example.assignment3

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.ui.theme.Assignment3Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var auth = Firebase.auth
    val genders = listOf("Male", "Female", "Other")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedState by remember { mutableStateOf(genders[0]) }
    var firstNameValue by remember { mutableStateOf("") }
    var lastNameValue by remember { mutableStateOf("") }
    var mobilePhoneValue by remember { mutableStateOf("") }
    var passwordCheckValue by rememberSaveable { mutableStateOf("") }
    var passwordForSubmit by rememberSaveable { mutableStateOf("") }
    val context: Context = LocalContext.current




    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Back Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { navController.navigate("Welcome") }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }

            }

            // Title
            Text(text = "Register", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = {
                    if (it.isNotEmpty()) {
                        email = it
                    }
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = email.isEmpty(),
            )
            Spacer(modifier = Modifier.height(8.dp))

            // First Name
            OutlinedTextField(
                value = firstNameValue,
                onValueChange = {
                    if (it.isNotEmpty()) {
                        firstNameValue = it
                    }
                },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = firstNameValue.isEmpty(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Last Name
            OutlinedTextField(
                value = lastNameValue,
                onValueChange = {
                    if (it.isNotEmpty()) {
                        lastNameValue = it
                    }
                },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = lastNameValue.isEmpty(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date of Birth
            DateOfBirthField(
                dateOfBirth = dateOfBirth,
                onDateSelected = { newDate -> dateOfBirth = newDate }
            )

            Spacer(modifier = Modifier.height(8.dp))
            //gender
            Column(modifier = Modifier.padding(vertical = 16.dp)) {

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it },
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .focusProperties {
                                canFocus = false
                            }
                            .padding(bottom = 8.dp),
                        readOnly = true,
                        value = selectedState,
                        onValueChange = {},
                        label = { Text("genders") },
                        //manages the arrow icon up and down
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    )
                    {
                        genders.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    selectedState = selectionOption
                                    isExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(8.dp))


            // Password Field
            OutlinedTextField(
                value = passwordCheckValue,
                onValueChange = {
                    passwordCheckValue = it
                    println("Current input: $it")
                },
                label = { Text("Password:(Min length 8, A-Z-0-9)") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = passwordCheckValue.isNotEmpty() && (!isPasswordValid(passwordCheckValue) || passwordCheckValue.length < 8),
            )


            Spacer(modifier = Modifier.height(8.dp))

                //Mobile Phone
                OutlinedTextField(
                    value = mobilePhoneValue,
                    onValueChange = {
                        if (it.isNotEmpty()) {
                            mobilePhoneValue = it
                        }
                    },
                    label = { Text("Mobile phone") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = mobilePhoneValue.isEmpty(),
                )

            Spacer(modifier = Modifier.height(16.dp))


            // Register Button
            Button(

                onClick = {
                    if (isPasswordValid(passwordCheckValue)) {
                        passwordForSubmit = passwordCheckValue
                        // Perform submit action here
                        println("Password submitted: $passwordForSubmit")


                    val user = User(
                        email = email,
                        firstName = firstNameValue,
                        lastName = lastNameValue,
                        dateOfBirth = dateOfBirth,
                        gender = selectedState,
                        password = passwordForSubmit,
                        mobilePhone = mobilePhoneValue
                    )





                    storeUserInDatabase(user)
                    auth.createUserWithEmailAndPassword(email, passwordForSubmit)
                        .addOnCompleteListener { task ->
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
                    }else {
                        Toast.makeText(context, "User info should meet the requirements",Toast.LENGTH_SHORT).show()
                        }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Register")
            }
        }
    }
}

fun isPasswordValid(password: String): Boolean {

    val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{8,})\$")
    val matcher = pattern.matcher(password)
    return matcher.matches()
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
    println(user)
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.reference.child("users")
    val emailKey = user.email.replace(".", "_")
    usersRef.child(emailKey).setValue(user)
}


