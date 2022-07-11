package com.github.kuya32.vintracker.feature_car.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AppNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@AppNavGraph
@Destination
@Composable
fun CarMapScreen(
    navigator: DestinationsNavigator,
    viewModel: CarViewModel = hiltViewModel()
) {
    
}