package com.github.kuya32.vintracker.feature_client.presentation.search_client

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kuya32.vintracker.feature_client.domain.models.Client
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SearchClientListingsViewModel @Inject constructor(

): ViewModel() {
    var state by mutableStateOf(SearchClientListingsState())

    private var searchJob: Job? = null

    init {
        getClientListings()
    }

    fun onEvent(event: SearchClientListingsEvent) {
        when(event) {
            is SearchClientListingsEvent.Refresh -> {
                getClientListings(fetchFromRemote = true)
            }
            is SearchClientListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getClientListings()
                }
            }
        }
    }

    private fun getClientListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        state = state.copy(
            clients = listOf(
                Client(fullName = "Marchael Acode", email = "kuya3232@gmail.com", phoneNumber = "3607086825"),
                Client(fullName = "Jordan Omoto", email = "jordanOmoto@gmail.com", phoneNumber = "2065543434"),
                Client(fullName = "Charley Lu", email = "charleyLu@gmail.com", phoneNumber = "2065123423")
            )
        )
    }

    fun assignRandomColor(): Color {
        val colors = arrayOf(
            Color(0xFFEFB8C8),
            Color(0xFF6650a4),
            Color(0xFF625b71),
            Color(0xFF7D5260),
            Color(0xFF193752),
            Color(0xFFb2c9cf),
            Color(0xFFff3e3e),
            Color(0xFF410001),
            Color(0xFF680003),
            Color(0xFF930006),
            Color(0xff330033),
            Color(0xff660066),
            Color(0xff990099)
        )
        return colors.random()
    }

    fun abbreviateClientName(fullName: String): String {
        val space = fullName.indexOf(" ")
        val firstLetterLastName = fullName.substring(space + 1, space + 2)
        println(firstLetterLastName)
        return "${fullName[0].uppercaseChar()}$firstLetterLastName"
    }
}