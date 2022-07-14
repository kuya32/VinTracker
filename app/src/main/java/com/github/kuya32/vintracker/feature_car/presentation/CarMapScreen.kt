package com.github.kuya32.vintracker.feature_car.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.core.presentation.navigation.AppNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.feature_car.domain.models.Car
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@AppNavGraph
@Destination
@Composable
fun CarMapScreen(
    car: Car,
    navigator: DestinationsNavigator,
    viewModel: CarViewModel = hiltViewModel(),
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(car.carCoordinates.latitude, car.carCoordinates.longitude), 10f)
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
            state = MarkerState(position = LatLng(car.carCoordinates.latitude, car.carCoordinates.longitude)),
            title = car.model,
            snippet = stringResource(id = R.string.marker_on_car, car.model )
        )
    }
}