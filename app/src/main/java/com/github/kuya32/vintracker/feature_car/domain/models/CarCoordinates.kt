package com.github.kuya32.vintracker.feature_car.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarCoordinates(
    val latitude: Double,
    val longitude: Double
): Parcelable
