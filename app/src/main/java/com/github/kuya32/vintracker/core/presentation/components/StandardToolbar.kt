package com.github.kuya32.vintracker.core.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.kuya32.vintracker.R
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun StandardToolbar(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    showBackArrow: Boolean = false,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    SmallTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = {
            if (showBackArrow) {
                IconButton(onClick = {
                    navigator.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_arrow),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        actions = navActions,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}