package com.github.kuya32.vintracker.feature_client.presentation.search_client

import com.github.kuya32.vintracker.feature_client.domain.models.Client

data class SearchClientListingsState(
    val clients: List<Client> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
