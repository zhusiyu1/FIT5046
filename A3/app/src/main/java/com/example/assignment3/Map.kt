package com.example.assignment3

import MapViewModel.MapViewModel
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import kotlinx.coroutines.launch

@Composable
fun MapGuide(navController: NavController?) {
    var clinicAddress by remember { mutableStateOf("") }
    val viewModel: MapViewModel = viewModel()
    val location by viewModel.location.observeAsState()
    val suggestions by viewModel.suggestions.observeAsState(emptyList())
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val mapInstance = remember { mutableStateOf<MapboxMap?>(null) }

    mapInstance.value?.let { viewModel.mapInstance = it }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Map Guide",
                style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Blue),
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
            )
            AndroidView(
                modifier = Modifier.weight(1f).fillMaxWidth().height(300.dp),
                factory = { context ->
                    MapView(context).also { mapView ->
                        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS)
                        mapInstance.value = mapView.getMapboxMap()
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = clinicAddress,
                onValueChange = { clinicAddress = it; viewModel.searchAddress(it) },
                label = { Text("Enter clinic address for guide") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text)
            )
            LazyColumn {
                items(suggestions) { suggestion ->
                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .clickable {
                                clinicAddress = suggestion
                                viewModel.handleAddressInput(suggestion)
                            }
                            .padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (clinicAddress.isNotEmpty()) {
                        viewModel.handleAddressInput(clinicAddress)
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please type again")
                        }
                    }
                },
                modifier = Modifier.width(200.dp).align(Alignment.CenterHorizontally)
            ) {
                Text("Search")
            }
        }
        SnackbarHost(hostState = snackbarHostState)
    }

    location?.let { point ->
        Log.d("MapUpdate", "Updating map to $point")
        viewModel.updateMapLocation(point)
    }
}