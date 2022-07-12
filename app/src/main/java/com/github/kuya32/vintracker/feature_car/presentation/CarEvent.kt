package com.github.kuya32.vintracker.feature_car.presentation

sealed class CarEvent {
    object InitiateGoogleMaps: CarEvent()
}
