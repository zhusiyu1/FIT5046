package com.example.assignment3

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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController?,
    healthViewModel: HealthViewModel = hiltViewModel()
) {
    val profileUiState by healthViewModel.profileUiState.collectAsState()
    val userUiState by healthViewModel.userUiState.collectAsState()

    val updatedUserState by remember {
        derivedStateOf {
            userUiState to profileUiState
        }
    }


    var edit by remember { mutableStateOf(false) }

    var email by remember {
        mutableStateOf(userUiState.email)
    }
    var firstName by remember {
        mutableStateOf(userUiState.firstName)
    }
    var lastName by remember {
        mutableStateOf(userUiState.lastName)
    }
    var dob by remember {
        mutableStateOf(userUiState.dateOfBirth)
    }
    var gender by remember {
        mutableStateOf(userUiState.gender)
    }
    var password by remember {
        mutableStateOf(userUiState.password)
    }
    var phone by remember {
        mutableStateOf(userUiState.mobilePhone)
    }

    var username by remember {
        mutableStateOf(profileUiState.username)
    }

    var address by remember {
        mutableStateOf(profileUiState.address)
    }


    val onUsernameChanged: (String) -> Unit = { username ->
        healthViewModel.username = username
    }
    val onAddressChanged: (String) -> Unit = { address ->
        healthViewModel.address = address
    }


    val updateProfile: () -> Unit = {
        healthViewModel.updateUserProfile(User(
            email = email,
            firstName = firstName,
            lastName = lastName,
            dateOfBirth = dob,
            gender = gender,
            password = password,
            mobilePhone = phone,
        ),
            updatedUsername = username,
            updatedAddress = address
        )
        edit = false
    }

    val toggleEditing: () -> Unit = {
        edit = !edit
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
            .padding(vertical = 32.dp, horizontal = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = "Profile",
                    color = Color(0xff1f41bb),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
            EditIconButton(onClick = { toggleEditing() }, modifier = Modifier.fillMaxWidth(1f))

        }
        // Email field
        Column(modifier = Modifier.padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Email",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier,
                email,
                { email = it }, edit
            )
        }
        // Username
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Username",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier, username, onUsernameChanged, edit
            )
        }
        // First name field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "First name",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier, firstName, {firstName = it }, edit
            )
        }
        // Last name field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Last name",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier, lastName, { lastName = it}, edit
            )
        }
        // DOB field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Date of Birth",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier,
                dob,
                { dob = it },
                edit
            )
        }
        // Gender
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Gender",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier, gender, { gender = it}, edit
            )
        }
        // Phone no field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Phone no",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier,
                phone,
                { phone = it},
                edit
            )
        }
        // Address field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Address",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier,
                address,
                onAddressChanged,
                edit
            )
        }
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Password",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier, password, { password = it}, edit
            )
        }
        if (edit) {
            Button(onClick = { updateProfile() }) {
                Text(text = "Update")
            }
        }
        Button(
            onClick = {
                Firebase.auth.signOut()
                navController!!.navigate("Welcome")
            },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Sign out")
        }

    }
}

@Composable
fun Property1Active(
    modifier: Modifier = Modifier,
    inputTextValue: String,
    onValueChange: (String) -> Unit,
    editting: Boolean
) {
    OutlinedTextField(
        value = inputTextValue, onValueChange = { onValueChange(it) }, modifier = modifier
            .requiredWidth(width = 325.dp)
            .background(color = Color(0xfff7f8f9)),
        enabled = editting
    )
}

@Composable
fun EditIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit",
        )
    }
}


@Preview
@Composable
private fun ProfilePreview() {
    Profile(Modifier, null, viewModel())
}
