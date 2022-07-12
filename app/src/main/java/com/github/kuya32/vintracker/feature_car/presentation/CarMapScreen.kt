package com.github.kuya32.vintracker.feature_car.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AppNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.feature_car.domain.models.Car
import com.github.kuya32.vintracker.feature_client.presentation.add_client.AddClientEvent
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@AppNavGraph
@Destination
@Composable
fun CarMapScreen(
    navigator: DestinationsNavigator,
    viewModel: CarViewModel = hiltViewModel(),
) {

}

@Composable
fun GoogleMap() {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.00, 0.00), 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            isMyLocationEnabled = true
        ),
        uiSettings = MapUiSettings(
            compassEnabled = true,
            myLocationButtonEnabled = true,
            rotationGesturesEnabled = true,
            zoomControlsEnabled = true,
            zoomGesturesEnabled = true
        )
    ) {
        Marker(
            state = MarkerState(position = LatLng(0.00, 0.00)),
            title = "CrossTrek",
            snippet = stringResource(id = R.string.marker_on_car, "CrossTrek" )
        )
    }
}