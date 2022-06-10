package com.github.kuya32.vintracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.kuya32.vintracker.ui.theme.VinTrackerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinTrackerTheme {
                Scaffold(
                    content = { innerPadding ->
                        ModalNavigationDrawer(
                            modifier = Modifier
                                .padding(innerPadding),
                            drawerContent = {
                                DrawerHeader()
                                DrawerBody(
                                    items = listOf(
                                        MenuItem(
                                            id = "home",
                                            title = stringResource(id = R.string.home),
                                            contentDescription = stringResource(id = R.string.go_to_home_screen),
                                            icon = Icons.Default.Home
                                        ),
                                        MenuItem(
                                            id = "settings",
                                            title = stringResource(id = R.string.settings),
                                            contentDescription = stringResource(id = R.string.go_to_settings_screen),
                                            icon = Icons.Default.Home
                                        ),
                                        MenuItem(
                                            id = "help",
                                            title = stringResource(id = R.string.help),
                                            contentDescription = stringResource(id = R.string.get_help),
                                            icon = Icons.Default.Home
                                        )
                                    ),
                                    onItemClick = {
                                        println("Clicked on ${it.title}")
                                    }
                                )
                            }
                        ) {

                        }
                    }
                )
            }
        }
    }
}