package com.github.kuya32.vintracker.feature_client.presentation.search_client

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.SearchClientNavGraph
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SearchClientNavGraph(start = true)
@Destination
@Composable
fun SearchClientListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchClientListingsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isRefreshing
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navigator = navigator,
            showBackArrow = true,
            title = {
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.client_search),
                )
            }
        )
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(SearchClientListingsEvent.OnSearchQueryChange(it))
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(SearchClientListingsEvent.Refresh) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.clients.size) { i ->
                    val client = state.clients[i]
                    ClientItem(
                        client = client,
                        viewModel = viewModel,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(16.dp)
                    )
                    if (i < state.clients.size) {
                        Divider(modifier = Modifier.padding(
                            horizontal = 16.dp
                        ))
                    }
                }
            }
        }
    }
}