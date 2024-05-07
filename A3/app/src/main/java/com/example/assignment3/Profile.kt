package com.example.assignment3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.example.assignment3.State.ProfileUiState

@Composable
fun Profile(modifier: Modifier = Modifier, navController: NavController?, healthViewModel: HealthViewModel = viewModel()) {
    val profileUiState by healthViewModel.profileUiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
            .padding(vertical = 32.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()) {
            BackButton(onClick = { goBack(navController!!)}, Modifier)
            Row ( horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Profile",
                    color = Color(0xff1f41bb),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
        // Email field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Email",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
            )

            Property1Active(Modifier,
                profileUiState.email
            )
        }
        // Username
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Username",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
            )

            Property1Active(
                Modifier, profileUiState.username
            )
        }
        // Fullname field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Full name",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
            )

            Property1Active(
                Modifier, profileUiState.fullName
            )
        }
        // Full name field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Phone no",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
            )

            Property1Active(
                Modifier, profileUiState.phone
            )
        }
        // DOB field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Date of Birth",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
                )

            Property1Active(
                Modifier,
                profileUiState.dob
            )
        }
        // Address field
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Address",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium),
               )

            Property1Active(
                Modifier,
                profileUiState.address
            )
        }
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "Past Appointments",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                items(3) { index ->
                    Text(
                        text = "Doctor Appointment ${index}", color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp,
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Property1Active(modifier: Modifier = Modifier, inputTextValue: String) {
    OutlinedTextField(value = inputTextValue, onValueChange = {},modifier = modifier
        .requiredWidth(width = 325.dp)
        .background(color = Color(0xfff7f8f9)),
        enabled = false
    )
}

//@Composable
//fun Property1Default1(modifier: Modifier = Modifier, profileUiState: ProfileUiState) {
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = modifier
//            .requiredWidth(width = 325.dp)
//            .requiredHeight(height = 50.dp)
//            .clip(shape = RoundedCornerShape(10.dp))
//            .background(color = Color(0xfff7f8f9))
//            .border(
//                border = BorderStroke(1.dp, Color(0xffe8ecf4)),
//                shape = RoundedCornerShape(10.dp)
//            )
//            .padding(all = 20.dp)
//    ) {
//        Text(
//            text = profileUiState.fullName,
//            color = Color(0xff1e232c).copy(alpha = 0.56f),
//            style = TextStyle(
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium),
//            modifier = Modifier
//                .fillMaxWidth())
//    }
//}

fun goBack(navController: NavController) {
    navController.navigate("Welcome")
}


@Composable
fun BackButton(
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
private fun ProfilePreview() {
    Profile(Modifier, null)
}
