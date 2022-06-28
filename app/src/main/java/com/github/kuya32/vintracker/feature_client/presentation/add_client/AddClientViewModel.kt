package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.kuya32.vintracker.core.domain.states.StandardTextFieldState
import com.github.kuya32.vintracker.core.utils.Constants
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AddClientViewModel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel() {

    init {
        Places.initialize(context, Constants.TEMP_GOOGLE_API_KEY)
    }

    val field = listOf(Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG)

    private val _clientFirstNameState = mutableStateOf(StandardTextFieldState())
    val clientFirstNameState: State<StandardTextFieldState> = _clientFirstNameState

    private val _clientLastNameState = mutableStateOf(StandardTextFieldState())
    val clientLastNameState: State<StandardTextFieldState> = _clientLastNameState

    private val _addClientState = mutableStateOf(AddClientState())
    val addClientState: State<AddClientState> = _addClientState

    fun onEvent(event: AddClientEvent) {
        when (event) {
            is AddClientEvent.EnteredClientFirstName -> {
                _clientFirstNameState.value = _clientFirstNameState.value.copy(
                    text = event.value
                )
            }
            is AddClientEvent.EnteredClientLastName -> {
                _clientLastNameState.value = _clientLastNameState.value.copy(
                    text = event.value
                )
            }
            is AddClientEvent.AddressTextFieldClicked -> {

            }
        }
    }
}