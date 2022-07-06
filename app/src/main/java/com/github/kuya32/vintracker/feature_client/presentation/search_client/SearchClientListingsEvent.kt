package com.github.kuya32.vintracker.feature_client.presentation.search_client

sealed class SearchClientListingsEvent {
    object Refresh: SearchClientListingsEvent()
    data class OnSearchQueryChange(val query: String): SearchClientListingsEvent()
}