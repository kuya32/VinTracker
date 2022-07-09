package com.github.kuya32.vintracker.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.appCurrentDestinationAsState
import com.github.kuya32.vintracker.destinations.Destination
import com.github.kuya32.vintracker.destinations.SearchCarScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun AppBar(
    navigator: DestinationsNavigator,
    navController: NavController,
    onHamburgerIconClick: () -> Unit,
    onBackIconClick: () -> Unit
) {
    val currentDestination: Destination? = navController.appCurrentDestinationAsState().value
    println("$currentDestination HELLO")
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            if (currentDestination == SearchCarScreenDestination) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.toggle_drawer),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            onHamburgerIconClick()
                        }
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_arrow),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            onBackIconClick()
                        }
                )
            }
        }
    )
}