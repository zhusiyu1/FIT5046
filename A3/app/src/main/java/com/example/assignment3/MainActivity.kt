package com.example.assignment3

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignment3.ui.theme.Assignment3Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.assignment3.DAO.BookingDao
import com.example.assignment3.DAO.HospitalDao
import com.example.assignment3.Database.HealthBookerRoomDatabase
import com.example.assignment3.HealthViewModel.HealthViewModel
import com.example.assignment3.Repository.HealthBookingRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    val healthViewModel: HealthViewModel by viewModels()

    @Inject lateinit var hospitalDao: HospitalDao
    @Inject lateinit var bookingDao: BookingDao
    @Inject lateinit var healthBookingRepository: HealthBookingRepository

    private val googleSignInRequestCode = 234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      applicationContext.deleteDatabase("HealthBookerRoomDatabase")

        val databaseScope = CoroutineScope(Dispatchers.IO)
        println("In Main")
        // Launch a coroutine to call the generate3Hospitals function
        databaseScope.launch {
            healthBookingRepository.generate3Hospitals()
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this,gso)
//
//        val googleSignInBtn = findViewById<SignInButton>(R.id.googleSignInBtn)
//        googleSignInBtn.setOnClickListener {
//            val signInIntent =  googleSignInClient.signInIntent
//            startActivityForResult(signInIntent,googleSignInRequestCode)
//        }



        setContent {
            Assignment3Theme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "Welcome") {
                        composable("Welcome") { Welcome(navController) }
                        composable("Login") { Login(navController) }
                        composable("Map") { MapGuide(navController) }
                        composable("Home") { Home(navController, healthViewModel) }
                        composable("Register") { RegisterPage(navController) }
                        composable("Navigation") { BottomNavigationBar(navController,healthViewModel) }

                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            googleSignInRequestCode -> {
                try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account)

                }catch (e:ApiException){
                    e.printStackTrace()
                    e.message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnSuccessListener {
                Toast.makeText(this, "Login Successful",Toast.LENGTH_SHORT).show()
                val mainIntent = Intent(this,MainActivity::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(mainIntent)
                finish()
            }
            .addOnFailureListener {
                it.printStackTrace()
                it.message?.let { it1 -> Toast.makeText(this, it1,Toast.LENGTH_SHORT).show() }
            }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


enum class Routes(val value: String) {
    Home("Home"),
    Map("Map"),
    Profile("Profile"),
    Appointment("Appointment"),
    Welcome("Welcome"),
//    Appointment("Booking"),
}





