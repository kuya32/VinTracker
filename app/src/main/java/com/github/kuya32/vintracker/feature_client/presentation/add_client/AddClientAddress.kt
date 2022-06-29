package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AddClientNavGraph
import com.github.kuya32.vintracker.feature_client.presentation.add_client.AddClientViewModel
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@AddClientNavGraph(start = true)
@Destination
@Composable
fun AddClientAddress(
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: AddClientViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, viewModel.field)
        .setCountry("US")
        .setTypeFilter(TypeFilter.ADDRESS)
        .build(context)
    val autoCompleteLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == Activity.RESULT_OK) {
            val place = it.data?.let { it1 -> Autocomplete.getPlaceFromIntent(it1) }
            val address = "${place?.addressComponents?.asList()?.get(0)?.name} ${place?.addressComponents?.asList()?.get(1)?.name}"
            val city = place?.addressComponents?.asList()?.get(3)?.name
            val state = place?.addressComponents?.asList()?.get(5)?.shortName
            val zipcode = place?.addressComponents?.asList()?.get(7)?.name
            println("$address $city, $state, $zipcode")
        }
    }
}