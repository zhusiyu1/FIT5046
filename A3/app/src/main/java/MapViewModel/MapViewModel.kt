package MapViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mapbox.api.geocoding.v5.MapboxGeocoding
import com.mapbox.api.geocoding.v5.models.GeocodingResponse
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MapViewModel : ViewModel() {
    private val accessToken = "sk.eyJ1IjoibGlhbmczMTIiLCJhIjoiY2x2dXN3cHA2MW5vdTJrcWlsYnBtNnA2NiJ9.8JdE185LfoRRIgnzihPFRA"
    private val _location = MutableLiveData<Point?>()
    val location: LiveData<Point?> = _location

    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>> = _suggestions

    var mapInstance: MapboxMap? = null

    fun handleAddressInput(address: String) {
        if (address.isEmpty()) {
            _location.postValue(null)
            _suggestions.postValue(emptyList())
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val point = geocode(address)
            withContext(Dispatchers.Main) {
                if (point != null) {
                    _location.postValue(point)
                    updateMapLocation(point)
                }
            }
        }
    }

    private fun geocode(address: String): Point? {
        if (address.isEmpty()) return null

        val geocoding = MapboxGeocoding.builder()
            .accessToken(accessToken)
            .query(address)
            .build()

        return try {
            val response: Response<GeocodingResponse> = geocoding.executeCall()
            if (response.isSuccessful) {
                response.body()?.features()?.firstOrNull()?.center()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun searchAddress(query: String) {
        if (query.isEmpty()) {
            _suggestions.postValue(emptyList())
            return
        }

        val geocoding = MapboxGeocoding.builder()
            .accessToken(accessToken)
            .query(query)
            .autocomplete(true)
            .build()

        geocoding.enqueueCall(object : Callback<GeocodingResponse> {
            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
                if (response.isSuccessful) {
                    _suggestions.postValue(response.body()?.features()?.map { it.placeName() ?: "" })
                } else {
                    _suggestions.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                _suggestions.postValue(emptyList())
            }
        })
    }

    fun updateMapLocation(point: Point) {
        mapInstance?.flyTo(
            CameraOptions.Builder()
                .center(point)
                .zoom(14.0)
                .build(),
            MapAnimationOptions.mapAnimationOptions {
                duration(2000)
            }
        )
    }
}