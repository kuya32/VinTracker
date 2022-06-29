package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.app.Activity.RESULT_OK
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AddClientNavGraph
import com.github.kuya32.vintracker.AppNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraLargeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraSmallSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
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

@RequiresApi(Build.VERSION_CODES.N)
@RootNavGraph(start = true)
//@AddClientNavGraph(start = true)
@Destination
@Composable
fun AddClientScreen(
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: AddClientViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val clientFirstNameState = viewModel.clientFirstNameState.value
    val clientLastNameState = viewModel.clientLastNameState.value
    val clientEmailState = viewModel.clientEmailState.value
    val clientPhoneNumberState = viewModel.clientPhoneNumberState.value
    val clientDateOfBirth = viewModel.clientDateOfBirth.value

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
                    text = stringResource(id = R.string.client_info),
                )
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumSpace)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(largeSpace)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    StandardTextField(
                        text = clientFirstNameState.text,
                        onValueChange = {
                            viewModel.onEvent(AddClientEvent.EnteredClientFirstName(it), null)
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
                            .padding(end = extraSmallSpace)
                            .weight(1f)
                    )
                    StandardTextField(
                        text = clientLastNameState.text,
                        onValueChange = {
                            viewModel.onEvent(AddClientEvent.EnteredClientFirstName(it), null)
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
                            .padding(start = extraSmallSpace)
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(largeSpace))
                StandardTextField(
                    text = clientEmailState.text,
                    onValueChange = {
                        viewModel.onEvent(AddClientEvent.EnteredClientEmail(it), null)
                    },
                    label = stringResource(id = R.string.email),
                    hint = "",
                    leadingIcon = Icons.Default.Email,
                    error = when (clientEmailState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.last_name_required)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(largeSpace))
                StandardTextField(
                    text = clientPhoneNumberState.text,
                    onValueChange = {
                        viewModel.onEvent(AddClientEvent.EnteredClientPhoneNumber(it), null)
                    },
                    label = stringResource(id = R.string.phone_number),
                    hint = "",
                    leadingIcon = Icons.Default.Phone,
                    error = when (clientPhoneNumberState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.last_name_required)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(largeSpace))
                StandardTextField(
                    text = clientDateOfBirth,
                    onValueChange = {},
                    isEnabled = false,
                    label = stringResource(id = R.string.date_of_birth),
                    hint = "",
                    leadingIcon = Icons.Default.Cake,
                    error = "",
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .clickable { viewModel.onEvent(AddClientEvent.DateOfBirthClicked, context) }
                )
            }
            Button(
                onClick = { /*TODO*/ },
                border = BorderStroke(
                    2.dp,
                    Color.White
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = largeSpace)
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
}