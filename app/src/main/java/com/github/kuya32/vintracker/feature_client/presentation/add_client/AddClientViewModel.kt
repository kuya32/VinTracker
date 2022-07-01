package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.domain.states.StandardTextFieldState
import com.github.kuya32.vintracker.core.utils.Constants
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddClientViewModel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel() {

    init {
        Places.initialize(context, "AIzaSyBmCz9bqFmBiBQVhspsUeGwWR2iWoQuKOI")
    }

    val field = listOf(Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG)
    val states: Array<String> = context.resources.getStringArray(R.array.states_list)

    private val _clientFirstNameState = mutableStateOf(StandardTextFieldState())
    val clientFirstNameState: State<StandardTextFieldState> = _clientFirstNameState

    private val _clientLastNameState = mutableStateOf(StandardTextFieldState())
    val clientLastNameState: State<StandardTextFieldState> = _clientLastNameState

    private val _clientEmailState = mutableStateOf(StandardTextFieldState())
    val clientEmailState: State<StandardTextFieldState> = _clientEmailState

    private val _clientPhoneNumberState = mutableStateOf(StandardTextFieldState())
    val clientPhoneNumberState: State<StandardTextFieldState> = _clientPhoneNumberState

    private val _clientDateOfBirthState = mutableStateOf("")
    val clientDateOfBirth: State<String> = _clientDateOfBirthState

    private val _clientAddressState = mutableStateOf(StandardTextFieldState())
    val clientAddressState: State<StandardTextFieldState> = _clientAddressState

    private val _clientOptionalAddressState = mutableStateOf(StandardTextFieldState())
    val clientOptionalAddressState: State<StandardTextFieldState> = _clientOptionalAddressState

    private val _clientCityState = mutableStateOf(StandardTextFieldState())
    val clientCityState: State<StandardTextFieldState> = _clientCityState

    private val _isExpanded = mutableStateOf(false)
    val isExpanded: State<Boolean> = _isExpanded

    private val _selectedDropdownTextState = mutableStateOf("")
    val selectedDropdownTextState: State<String> = _selectedDropdownTextState

    private val _clientZipcodeState = mutableStateOf(StandardTextFieldState())
    val clientZipcodeState: State<StandardTextFieldState> = _clientZipcodeState

    private val _clientLicenseState = mutableStateOf(StandardTextFieldState())
    val clientLicenseState: State<StandardTextFieldState> = _clientLicenseState

    private val _isCameraSelected = mutableStateOf(false)
    val isCameraSelected: State<Boolean> = _isCameraSelected

    private val _licenseImageUri = mutableStateOf<Uri?>(null)
    var licenseImageUri: State<Uri?> = _licenseImageUri

    private val _licenseBitmap = mutableStateOf<Bitmap?>(null)
    val licenseBitmap: State<Bitmap?> = _licenseBitmap

    private val _addClientState = mutableStateOf(AddClientState())
    val addClientState: State<AddClientState> = _addClientState

    @RequiresApi(Build.VERSION_CODES.N)
    fun onEvent(
        event: AddClientEvent,
        context: Context? = null,
        clientAddress: List<String>? = null,
        licenseUri: Uri? = null,
        licenseBitmap: Bitmap? = null
    ) {
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
            is AddClientEvent.EnteredClientEmail -> {
                _clientEmailState.value = _clientEmailState.value.copy(
                    text = event.value
                )
            }
            is AddClientEvent.EnteredClientPhoneNumber -> {
                _clientPhoneNumberState.value = _clientPhoneNumberState.value.copy(
                    text = event.value
                )
            }
            is AddClientEvent.DateOfBirthClicked -> {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                calendar.time = Date()

                context?.let {
                    DatePickerDialog(context,
                        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                            _clientDateOfBirthState.value = "$month/$dayOfMonth/$year"
                        }, year, month, day
                    ).show()
                }
            }
            is AddClientEvent.ClientAddressChosen -> {
                _clientAddressState.value = _clientAddressState.value.copy(
                    text = clientAddress?.get(0).toString()
                )
                _clientCityState.value = _clientCityState.value.copy(
                    text = clientAddress?.get(1).toString()
                )
                _selectedDropdownTextState.value = clientAddress?.get(2).toString()
                _clientZipcodeState.value = _clientZipcodeState.value.copy(
                    text = clientAddress?.get(3).toString()
                )
            }
            is AddClientEvent.EnteredClientOptionalAddress -> {
                _clientOptionalAddressState.value = _clientOptionalAddressState.value.copy(
                    text = event.value
                )
            }
            is AddClientEvent.StateDropdownClicked -> {
                _isExpanded.value = !_isExpanded.value
            }
            is AddClientEvent.ChooseClientState -> {
                _selectedDropdownTextState.value = clientAddress?.get(0).toString()
            }
            is AddClientEvent.LaunchCameraForLicense -> {
                _licenseImageUri.let {
                    if (!isCameraSelected.value) {
                        
                    }
                }
            }
            is AddClientEvent.LaunchGalleryForLicense -> {

            }
            is AddClientEvent.ChooseCamera -> {
                _isCameraSelected.value = true
            }
            is AddClientEvent.ChooseFromGallery -> {
                _isCameraSelected.value = false
            }
        }
    }
}