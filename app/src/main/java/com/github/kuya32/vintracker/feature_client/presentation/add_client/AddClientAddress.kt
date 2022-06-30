package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AddClientNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.github.kuya32.vintracker.feature_client.domain.models.Client
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RequiresApi(Build.VERSION_CODES.N)
@AddClientNavGraph
@Destination
@Composable
fun AddClientAddress(
    client: Client,
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: AddClientViewModel = hiltViewModel(),
) {
    val clientAddressState = viewModel.clientAddressState.value
    val clientOptionalAddressState = viewModel.clientOptionalAddressState.value
    val clientCityState = viewModel.clientCityState.value
    var selectedDropdownTextState = viewModel.selectedDropdownTextState.value
    val clientZipcodeState = viewModel.clientZipcodeState.value
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
                viewModel.onEvent(AddClientEvent.ClientAddressChosen, null, addressList)
            }
        }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
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
                    modifier = Modifier.padding(smallSpace)
                ) {
                    TextField(
                        value = selectedDropdownTextState,
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = stringResource(id = R.string.state),
                        trailingIcon = Icon(
                            imageVector = if (viewModel.isExpanded.value)
                                Icons.Filled.KeyboardArrowUp
                            else
                                Icons.Filled.KeyboardArrowDown,
                            contentDescription = "contentDescription",
                            modifier = Modifier.clickable {  }
                        )
                    )
                }
            }
        }
    }
}