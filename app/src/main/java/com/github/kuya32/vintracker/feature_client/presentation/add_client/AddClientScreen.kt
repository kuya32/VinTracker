package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AppNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraLargeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraSmallSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.feature_auth.presentation.sign_up.SignUpEvent
import com.github.kuya32.vintracker.feature_auth.presentation.sign_up.SignUpViewModel
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
//@AppNavGraph
@Destination
@Composable
fun AddClientScreen(
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: AddClientViewModel = hiltViewModel()
) {
    val clientFirstNameState = viewModel.clientFirstNameState.value
    val clientLastNameState = viewModel.clientLastNameState.value
    val context = LocalContext.current
    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, viewModel.field)
        .setCountry("US")
        .setTypeFilter(TypeFilter.ADDRESS)
        .build(context)
    val autoCompleteLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == RESULT_OK) {
            val place = it.data?.let { it1 -> Autocomplete.getPlaceFromIntent(it1) }
            clientFirstNameState.text = place?.address.toString()
        }
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumSpace)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.add_a_client),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = mediumSpace)
                )
                Spacer(modifier = Modifier.height(extraLargeSpace))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    StandardTextField(
                        text = clientFirstNameState.text,
                        onValueChange = {
                            viewModel.onEvent(AddClientEvent.EnteredClientFirstName(it))
                        },
                        label = stringResource(id = R.string.first_name),
                        hint = "",
                        leadingIcon = Icons.Default.Person,
                        error = when (clientFirstNameState.error) {
                            is AuthErrors.FieldEmpty -> {
                                stringResource(id = R.string.first_name_required)
                            }
                            else -> ""
                        },
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Right)
                        }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .padding(start = mediumSpace, end = extraSmallSpace)
                            .weight(1f)
                    )
                    StandardTextField(
                        text = clientLastNameState.text,
                        onValueChange = {
                            viewModel.onEvent(AddClientEvent.EnteredClientFirstName(it))
                        },
                        label = stringResource(id = R.string.last_name),
                        hint = "",
                        leadingIcon = null,
                        error = when (clientLastNameState.error) {
                            is AuthErrors.FieldEmpty -> {
                                stringResource(id = R.string.last_name_required)
                            }
                            else -> ""
                        },
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .padding(start = extraSmallSpace, end = mediumSpace)
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(mediumSpace))
                Button(onClick = { autoCompleteLauncher.launch(intent) }) {
                    Text(text = "Hello)")
                }
            }
        }
    }
}