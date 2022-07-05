package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AddClientNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraSmallSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.destinations.AddClientAddressScreenDestination
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.github.kuya32.vintracker.feature_client.domain.models.Client
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RequiresApi(Build.VERSION_CODES.Q  )
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
    val clientNotesState = viewModel.clientNotesState.value

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
                        text = stringResource(id = R.string.client_info),
                    )
                }
            )
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
                        }),
                        singleLine = true,
                        modifier = Modifier
                            .padding(end = extraSmallSpace)
                            .weight(1f)
                    )
                    StandardTextField(
                        text = clientLastNameState.text,
                        onValueChange = {
                            viewModel.onEvent(AddClientEvent.EnteredClientLastName(it))
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
                        viewModel.onEvent(AddClientEvent.EnteredClientEmail(it))
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
                        viewModel.onEvent(AddClientEvent.EnteredClientPhoneNumber(it))
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
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
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
                    singleLine = true,
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(
                                event = AddClientEvent.DateOfBirthClicked,
                                context = context
                            )
                        }
                )
                Spacer(modifier = Modifier.height(largeSpace))
                // TODO: Notes input does not show multiple lines
                StandardTextField(
                    text = clientNotesState.text,
                    onValueChange = {
                        viewModel.onEvent(AddClientEvent.EnteredClientNotes(it))
                    },
                    label = stringResource(id = R.string.notes),
                    hint = "",
                    leadingIcon = Icons.Default.Summarize,
                    error = when (clientNotesState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.license_required)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    singleLine = true,
                    maxLength = 500,
                    maxLines = 5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.White, RoundedCornerShape(smallSpace)),
                    shape = RoundedCornerShape(smallSpace)
                )
            }
        }
        Button(
            onClick = {
                navigator.navigate(
                    AddClientAddressScreenDestination(
                        client = Client(
                            fullName = "${clientFirstNameState.text} ${clientLastNameState.text}",
                            email = clientEmailState.text,
                            phoneNumber = clientPhoneNumberState.text,
                            dateOfBirth = clientDateOfBirth
                        )
                    )
                )
            },
            border = BorderStroke(
                2.dp,
                MaterialTheme.colorScheme.background
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

@AddClientNavGraph(start = true)
@Destination
@Composable
fun randomComposable() {

}