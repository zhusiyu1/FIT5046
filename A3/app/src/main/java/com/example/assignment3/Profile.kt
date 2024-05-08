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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment3.HealthViewModel.HealthViewModel

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController?,
    healthViewModel: HealthViewModel
) {
    val profileUiState by healthViewModel.profileUiState.collectAsState()

    var edit by remember { mutableStateOf(false) }

    // Event handlers for input field changes
    val onFullNameChanged: (String) -> Unit = { fullName ->
        healthViewModel.fullName = fullName
    }
    val onUsernameChanged: (String) -> Unit = { username ->
        healthViewModel.username = username
    }
    val onEmailChanged: (String) -> Unit = { email ->
        healthViewModel.email = email
    }
    val onPhoneChanged: (String) -> Unit = { phone ->
        healthViewModel.phone = phone
    }
    val onDobChanged: (String) -> Unit = { dob ->
        healthViewModel.dob = dob
    }
    val onAddressChanged: (String) -> Unit = { address ->
        healthViewModel.address = address
    }

    val updateProfile: () -> Unit = {
        healthViewModel.updateUserProfile()
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
            .padding(vertical = 32.dp, horizontal = 32.dp),
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
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
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
                profileUiState.email,
                onEmailChanged, edit
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
                Modifier, profileUiState.username, onUsernameChanged, edit
            )
        }
        // Fullname field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Full name",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
            )

            Property1Active(
                Modifier, profileUiState.fullName, onFullNameChanged, edit
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
                profileUiState.phone,
                onPhoneChanged,
                edit
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
                profileUiState.dob,
                onDobChanged,
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
                profileUiState.address,
                onAddressChanged,
                edit
            )
        }
        if (edit) {
            Button(onClick = { updateProfile() }) {
                Text(text = "Update")
            }
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
