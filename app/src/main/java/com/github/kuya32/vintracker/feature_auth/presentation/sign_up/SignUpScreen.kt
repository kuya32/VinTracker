package com.github.kuya32.vintracker.feature_auth.presentation.sign_up

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.ui.theme.*
import com.github.kuya32.vintracker.destinations.MainAppScreenDestination
import com.github.kuya32.vintracker.feature_auth.presentation.login.LoginEvent
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun SignUpScreen(
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val firstNameState = viewModel.firstNameState.value
    val lastNameState = viewModel.lastNameState.value
    val phoneNumberState = viewModel.phoneNumberState.value
    val usernameState = viewModel.usernameState.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val confirmationPasswordState = viewModel.confirmPasswordState.value
    val scrollState = rememberScrollState()

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
               horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up),
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
                        text = firstNameState.text,
                        onValueChange = {
                            viewModel.onEvent(SignUpEvent.EnteredFirstName(it))
                        },
                        label = stringResource(id = R.string.first_name),
                        hint = "",
                        leadingIcon = Icons.Default.Person,
                        error = when (emailState.error) {
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
                        text = lastNameState.text,
                        onValueChange = {
                            viewModel.onEvent(SignUpEvent.EnteredLastName(it))
                        },
                        label = stringResource(id = R.string.last_name),
                        hint = "",
                        leadingIcon = null,
                        error = when (emailState.error) {
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
                StandardTextField(
                    text = phoneNumberState.text,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredPhoneNumber(it))
                    },
                    label = stringResource(id = R.string.phone_number),
                    hint = "",
                    leadingIcon = Icons.Default.Phone,
                    error = when (phoneNumberState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.phone_number_required)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = mediumSpace)
                )
                Spacer(modifier = Modifier.height(mediumSpace))
                StandardTextField(
                    text = usernameState.text,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredUsername(it))
                    },
                    label = stringResource(id = R.string.username),
                    hint = "",
                    leadingIcon = Icons.Default.PersonOutline,
                    error = when (emailState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.username_required)
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
                        .padding(horizontal = mediumSpace)
                )
                Spacer(modifier = Modifier.height(mediumSpace))
                StandardTextField(
                    text = emailState.text,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredEmail(it))
                    },
                    label = stringResource(id = R.string.email),
                    hint = "",
                    leadingIcon = Icons.Default.Email,
                    error = when (emailState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.email_required)
                        }
                        is AuthErrors.InvalidEmail -> {
                            stringResource(id = R.string.invalid_email)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = mediumSpace)
                )
                Spacer(modifier = Modifier.height(mediumSpace))
                StandardTextField(
                    text = passwordState.text,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredPassword(it))
                    },
                    label = stringResource(id = R.string.password),
                    hint = "",
                    leadingIcon = Icons.Default.Lock,
                    error = when (emailState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.password_required)
                        }
                        is AuthErrors.InvalidPassword -> {
                            stringResource(id = R.string.invalid_password)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = mediumSpace),
                    isPasswordVisible = passwordState.isPasswordVisible,
                    onPasswordToggleClick = {
                        viewModel.onEvent(SignUpEvent.ToggledPasswordVisibility)
                    }
                )
                Spacer(modifier = Modifier.height(mediumSpace))
                StandardTextField(
                    text = confirmationPasswordState.text,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvent.EnteredConfirmPassword(it))
                    },
                    label = stringResource(id = R.string.confirm_password),
                    hint = "",
                    leadingIcon = Icons.Default.LockOpen,
                    error = when (emailState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.username_required)
                        }
                        is AuthErrors.PasswordDoesNotMatch -> {
                            stringResource(id = R.string.password_does_not_match)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = mediumSpace),
                    isPasswordVisible = confirmationPasswordState.isPasswordVisible,
                    onPasswordToggleClick = {
                        viewModel.onEvent(SignUpEvent.ToggledConfirmationPasswordVisibility)
                    }
                )
                Spacer(modifier = Modifier.height(smallSpace))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(mediumSpace)
                ) {
                    Checkbox(
                        checked = viewModel.isBoxCheckedState.value,
                        onCheckedChange = {
                            viewModel.onEvent(SignUpEvent.ToggledCheckBox)
                        }
                    )
                    Spacer(modifier = Modifier.width(smallSpace))
                    Text(
                        text = stringResource(id = R.string.agree_to_terms_and_services),
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                }
                Button(
                    onClick = {
                          // TODO: Submit user account credentials and then navigate back to login screen
                    },
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
                    contentPadding = PaddingValues(mediumSpace),
                ) {
                    Text(
                        text = stringResource(id = R.string.submit),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}