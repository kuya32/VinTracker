package com.github.kuya32.vintracker.feature_car.domain.models

data class Car(
    val carImage: String = "",
    val year: String,
    val make: String,
    val model: String,
    val color: String = "White",
    val price: String = "$20,000",
    val vin: String = "",
    val carCoordinates: CarCoordinates = CarCoordinates(0.00, 0.00),
    val isAvailable: Boolean = false,
    val checkedOutLast: String = "",
)
