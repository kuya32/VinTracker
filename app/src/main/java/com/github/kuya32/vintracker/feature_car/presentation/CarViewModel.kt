package com.github.kuya32.vintracker.feature_car.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(

): ViewModel() {

    private val _isAvailable = mutableStateOf(true)
    val isAvailable: State<Boolean> = _isAvailable

    private val _permissionsGranted = mutableStateOf(false)
    val permissionsGranted: State<Boolean> = _permissionsGranted

    fun onEvent(event: CarEvent) {

    }
}