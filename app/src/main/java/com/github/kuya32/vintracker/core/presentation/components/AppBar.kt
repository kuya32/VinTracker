package com.github.kuya32.vintracker.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.kuya32.vintracker.R

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = stringResource(id = R.string.toggle_drawer),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                    onNavigationIconClick()
                    }
            )
        }
    )
}