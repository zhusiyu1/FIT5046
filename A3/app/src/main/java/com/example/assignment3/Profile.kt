package com.example.assignment3

import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController,
    navControllerMainAcitivity: NavController,
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

    val onUsernameChanged: (String) -> Unit = { username ->
        healthViewModel.username = username
    }
    val onAddressChanged: (String) -> Unit = { address ->
        healthViewModel.address = address
    }

    val genders = listOf("Male", "Female", "Other")
    var email by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.first.email)
    }
    var firstName by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.first.firstName)
    }
    var lastName by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.first.lastName)
    }
    var dob by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.first.dateOfBirth)
    }
    var gender by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.first.gender)
    }
    var phone by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.first.mobilePhone)
    }
    var username by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.second.username)
    }
    var address by remember(updatedUserState, navControllerMainAcitivity) {
        mutableStateOf(updatedUserState.second.address)
    }

    var isExpanded by remember { mutableStateOf(false) }
    var passwordCheckValue by rememberSaveable { mutableStateOf(updatedUserState.first.password) }
    var passwordForSubmit by rememberSaveable { mutableStateOf("") }
    val context: Context = LocalContext.current

    val updateProfile: () -> Unit = {
        if (passwordForSubmit.isEmpty()) {

            try {
                healthViewModel.updateUserProfile(
                    User(
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        dateOfBirth = dob,
                        gender = gender,
                        password = passwordForSubmit,
                        mobilePhone = phone,
                    ),
                    updatedUsername = username,
                    updatedAddress = address
                )
                edit = false
            } catch (e: Exception) {
                Toast.makeText(context, "Form is not valid", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Form is not valid", Toast.LENGTH_SHORT).show()
        }
    }

    val toggleEditing: () -> Unit = {
        edit = !edit
    }

    val logout: () -> Unit = {
        Firebase.auth.signOut()
        healthViewModel.resetUserUiState()
    }

    // https://stackoverflow.com/questions/73859164/how-do-i-make-a-disabled-compose-outlinedtextfield-look-like-its-enabled
    // How do I make a disabled compose OutlinedTextField look like its enabled? (n.d.). Stack Overflow. Retrieved May 10, 2024, from https://stackoverflow.com/questions/73859164/how-do-i-make-a-disabled-compose-outlinedtextfield-look-like-its-enabled

    val enabledColors = OutlinedTextFieldDefaults.colors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = Color.Transparent,
        disabledBorderColor = MaterialTheme.colorScheme.outline,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(50.dp))
            .background(color = Color.White)
            .padding(vertical = 32.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Profile", style = MaterialTheme.typography.headlineMedium)

            }

            if (!edit) {
                EditIconButton(onClick = { toggleEditing() }, modifier = Modifier.fillMaxWidth(1f))
            } else {
                CloseIconButton(onClick = { toggleEditing() }, modifier = Modifier.fillMaxWidth(1f))
            }
        }
        if (edit) Text("Editing")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            item {
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
                    enabled = edit,
                    colors = enabledColors
                )
                Spacer(modifier = Modifier.height(8.dp))

                // First Name
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        if (it.isNotEmpty()) {
                            firstName = it
                        }
                    },
                    label = { Text("First Name") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = firstName.isEmpty(),
                    enabled = edit,
                    colors = enabledColors
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Last Name
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        if (it.isNotEmpty()) {
                            lastName = it
                        }
                    },
                    label = { Text("Last Name") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = lastName.isEmpty(),
                    enabled = edit,
                    colors = enabledColors
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Date of Birth
                DateOfBirthFieldEdittable(
                    dateOfBirth = dob,
                    onDateSelected = { newDate -> dob = newDate },
                    edit,
                    colors = enabledColors
                )

                Spacer(modifier = Modifier.height(8.dp))


                // Address
                OutlinedTextField(
                    value = address,
                    onValueChange = {
                        if (it.isNotEmpty()) {
                            address = it
                        }
                    },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = address.isEmpty(),
                    enabled = edit,
                    colors = enabledColors
                )

                Spacer(modifier = Modifier.height(8.dp))
                //gender
                Column(modifier = Modifier.padding(vertical = 16.dp)) {

                    ExposedDropdownMenuBox(
                        expanded = isExpanded,
                        onExpandedChange = { if (edit) isExpanded = it },
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
                            value = gender,
                            onValueChange = {},
                            label = { Text("Genders") },
                            //manages the arrow icon up and down
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                            },
                            enabled = edit,
                            colors = enabledColors
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
                                        gender = selectionOption
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
                    isError = passwordCheckValue.isNotEmpty() && (!isPasswordValid(
                        passwordCheckValue
                    ) || passwordCheckValue.length < 8),
                    enabled = edit,
                    colors = enabledColors
                )


                Spacer(modifier = Modifier.height(8.dp))

                //Mobile Phone
                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        if (it.isNotEmpty()) {
                            phone = it
                        }
                    },
                    label = { Text("Mobile phone") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = phone.isEmpty(),
                    enabled = edit,
                    colors = enabledColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (edit) {
                    Button(onClick = { updateProfile() }) {
                        Text(text = "Update")
                    }
                }
                Button(
                    onClick = {
                        logout()
                        navControllerMainAcitivity!!.navigate("Welcome")
//                navigate("Welcome")
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Sign out")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateOfBirthFieldEdittable(
    dateOfBirth: String,
    onDateSelected: (String) -> Unit,
    edit: Boolean,
    colors: TextFieldColors
) {
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
        },
        enabled = edit,
        colors = colors
    )
}

@Composable
fun Property1Active(
    modifier: Modifier = Modifier,
    inputTextValue: String,
    onValueChange: (String) -> Unit,
    editting: Boolean
) {
    OutlinedTextField(
        value = inputTextValue, onValueChange = { onValueChange(it) },
        modifier = modifier
            .requiredWidth(width = 325.dp)
            .background(color = Color(0xfff7f8f9)),
        enabled = editting,
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

@Composable
fun CloseIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Edit",
        )
    }
}


//@Preview
//@Composable
//private fun ProfilePreview() {
//    Profile(Modifier, null, viewModel())
//}
