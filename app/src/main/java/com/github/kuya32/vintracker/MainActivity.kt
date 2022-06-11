package com.github.kuya32.vintracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.kuya32.vintracker.ui.theme.VinTrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinTrackerTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
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
                                    id = "add a client",
                                    title = stringResource(id = R.string.add_a_client),
                                    contentDescription = stringResource(id = R.string.add_a_client),
                                    icon = Icons.Default.PersonAdd
                                ),
                                MenuItem(
                                    id = "search for client",
                                    title = stringResource(id = R.string.search_for_client),
                                    contentDescription = stringResource(id = R.string.search_for_client),
                                    icon = Icons.Default.PersonSearch
                                ),
                                MenuItem(
                                    id = "help",
                                    title = stringResource(id = R.string.help),
                                    contentDescription = stringResource(id = R.string.get_help),
                                    icon = Icons.Default.Help
                                )
                            ),
                            onItemClick = {
                                println("Clicked on ${it.title}")
                            }
                        )
                    },
                    content = {
                        Scaffold(
                            topBar = {
                                AppBar(
                                    onNavigationIconClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            },
                            content = { innerPadding ->
                                Box(modifier = Modifier.padding(innerPadding))
                            }
                        )
                    }
                )
            }
        }
    }
}