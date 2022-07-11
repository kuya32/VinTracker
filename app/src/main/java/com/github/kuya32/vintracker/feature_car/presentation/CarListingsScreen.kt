package com.github.kuya32.vintracker.feature_car.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AppNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.destinations.CarDetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
//@AppNavGraph
@Destination
@Composable
fun CarListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: CarViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .padding(mediumSpace)
            .clip(RoundedCornerShape(24.dp))
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(20) {
                Image(
                    painter = painterResource(id = R.drawable.ic_vintracker_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            navigator.navigate(CarDetailScreenDestination())
                        }
                )
            }
        }
    }
}