package com.example.assignment3

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase


@Composable
fun Login(navController: NavController, healthViewModel: HealthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPassValid by remember { mutableStateOf(false) }

    // Google Signin
    // https://stackoverflow.com/questions/72563673/google-authentication-with-firebase-and-jetpack-compose
    val context = LocalContext.current
    val token = stringResource(R.string.web_client_id)
    val launcherNav = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        navController.navigate("Navigation")
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        val task =
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                    .getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                        }
                    }
            }
            catch (e: ApiException) {
                Log.w("TAG", "GoogleSign in Failed", e)
            }
    }

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
            onValueChange = { email = it
                isEmailValid = it.isNotEmpty()},
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 270.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            isError = !isEmailValid
        )



        OutlinedTextField(
            value = password,
            onValueChange = { password = it
                isPassValid = it.isNotEmpty()},
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(),
            isError = !isPassValid
        )

        Column {
            if (!isEmailValid) {
                Text(text = "The email cannot be empty", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
            }
            if (!isPassValid) {
                Text(text = "The password cannot be empty", color = Color.Red, modifier = Modifier.padding(start = 16.dp))
            }
        }



        Button(
            onClick = {
                val gso = GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(token)
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)

                val user = User(
                    email = "",
                    firstName = "",
                    lastName = "",
                    dateOfBirth = "",
                    gender = "",
                    password = "",
                    mobilePhone = ""
                )

                storeUserInDatabaseNew(user)

                navController.navigate("Navigation")
            },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 300.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text("Google Account")
        }


        Button(
            onClick = {
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate("Navigation")
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                        }
                    }
            },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 200.dp)
                .align(Alignment.BottomCenter)
                .width(200.dp),
        ) {
            Text("Login")
        }



        Button(
            onClick = {navController.navigate("Register") },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 150.dp)
                .align(Alignment.BottomCenter)
                .width(200.dp),
        ) {
            Text("Register")
        }

    }

}

fun storeUserInDatabaseNew(user: User) {
    val database = FirebaseDatabase.getInstance()
    val usersRef = database.reference.child("users")
    val emailKey = user.email.replace(".", "_")
    usersRef.child(emailKey).setValue(user)
}