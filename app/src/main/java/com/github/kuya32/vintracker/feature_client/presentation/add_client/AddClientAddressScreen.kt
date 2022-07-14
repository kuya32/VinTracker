package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.core.presentation.navigation.AddClientNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraSmallSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.destinations.AddClientLicenseScreenDestination
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.github.kuya32.vintracker.feature_client.domain.models.Client
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RequiresApi(Build.VERSION_CODES.Q)
@AddClientNavGraph
@Destination
@Composable
fun AddClientAddressScreen(
    client: Client,
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: AddClientViewModel = hiltViewModel(),
) {
    val clientAddressState = viewModel.clientAddressState.value
    val clientOptionalAddressState = viewModel.clientOptionalAddressState.value
    val clientCityState = viewModel.clientCityState.value
    val selectedDropdownTextState = viewModel.selectedDropdownTextState.value
    val clientZipcodeState = viewModel.clientZipcodeState.value
    val clientAddressText = clientAddressState.text
    val clientCityText = clientCityState.text
    val clientZipcodeText = clientZipcodeState.text

    val context = LocalContext.current
    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, viewModel.field)
        .setCountry("US")
        .setTypeFilter(TypeFilter.ADDRESS)
        .build(context)

    val autoCompleteLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == Activity.RESULT_OK) {
                val place = it.data?.let { it1 -> Autocomplete.getPlaceFromIntent(it1) }
                val address = "${
                    place?.addressComponents?.asList()?.get(0)?.name
                } ${place?.addressComponents?.asList()?.get(1)?.name}"
                val city = place?.addressComponents?.asList()?.get(3)?.name.toString()
                val state = place?.addressComponents?.asList()?.get(5)?.shortName.toString()
                val zipcode = place?.addressComponents?.asList()?.get(7)?.name.toString()
                val addressList = listOf(address, city, state, zipcode)
                viewModel.onEvent(
                    event = AddClientEvent.ClientAddressChosen,
                    clientAddress =  addressList
                )
            }
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            StandardToolbar(
                navigator = navigator,
                showBackArrow = true,
                title = {
                    Text(
                        text = stringResource(id = R.string.client_address),
                    )
                }
            )
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(largeSpace)
            ) {
                StandardTextField(
                    text = clientAddressState.text,
                    onValueChange = {},
                    isEnabled = false,
                    label = stringResource(id = R.string.address),
                    hint = "",
                    error = when (clientAddressState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.address_required)
                        }
                        else -> ""
                    },
                    singleLine = true,
                    modifier = Modifier
                        .clickable { autoCompleteLauncher.launch(intent) }
                )
                Spacer(modifier = Modifier.height(largeSpace))
                StandardTextField(
                    text = clientOptionalAddressState.text,
                    onValueChange = {
                        viewModel.onEvent(AddClientEvent.EnteredClientOptionalAddress(it), null)
                    },
                    hint = stringResource(id = R.string.address_optional),
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(largeSpace))
                StandardTextField(
                    text = clientCityState.text,
                    onValueChange = {},
                    label = stringResource(id = R.string.city),
                    hint = "",
                    error = when (clientAddressState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.city_required)
                        }
                        else -> ""
                    },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(largeSpace))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(end = smallSpace)
                            .weight(1f)
                    ) {
                        TextField(
                            value = selectedDropdownTextState,
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = {
                                Text(text = stringResource(id = R.string.state))
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = if (viewModel.isExpanded.value)
                                        Icons.Filled.KeyboardArrowUp
                                    else
                                        Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "contentDescription",
                                    modifier = Modifier.clickable { viewModel.onEvent(AddClientEvent.StateDropdownClicked) }
                                )
                            },
                        )
                        DropdownMenu(
                            expanded = viewModel.isExpanded.value,
                            onDismissRequest = { viewModel.onEvent(AddClientEvent.StateDropdownClicked) },
                        ) {
                            viewModel.states.forEach { label ->
                                DropdownMenuItem(
                                    text = {
                                        Text(text = label)
                                    },
                                    onClick = {
                                        viewModel.onEvent(
                                            event = AddClientEvent.ChooseClientState,
                                            clientAddress = listOf(label)
                                        )
                                        viewModel.onEvent(AddClientEvent.StateDropdownClicked)
                                    })
                            }
                        }
                    }
                    StandardTextField(
                        text = clientZipcodeState.text,
                        onValueChange = {},
                        modifier = Modifier
                            .padding(start = smallSpace)
                            .weight(1f),
                        label = stringResource(id = R.string.zipcode),
                        hint = "",
                        error = when (clientZipcodeState.error) {
                            is AuthErrors.FieldEmpty -> {
                                stringResource(id = R.string.zipcode_required)
                            }
                            else -> ""
                        },
                        singleLine = true
                    )
                }
            }
        }
        Button(
            onClick = {
                      navigator.navigate(
                          AddClientLicenseScreenDestination(
                              client = Client(
                                  fullName = client.fullName,
                                  email = client.email,
                                  phoneNumber = client.phoneNumber,
                                  dateOfBirth = client.dateOfBirth,
                                  address = "$clientAddressText $clientCityText, $selectedDropdownTextState, $clientZipcodeText"
                              )
                          )
                      )
            },
            border = BorderStroke(
                2.dp,
                Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = largeSpace, end = largeSpace, bottom = largeSpace)
        ) {
            Text(
                text = stringResource(id = R.string.next),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(extraSmallSpace))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = stringResource(id = R.string.next),
                modifier = Modifier
                    .size(16.dp)
            )
        }
    }
}