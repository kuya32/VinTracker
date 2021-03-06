package com.github.kuya32.vintracker.feature_auth.presentation.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.destinations.ForgotPasswordScreenDestination
import com.github.kuya32.vintracker.destinations.MainAppScreenDestination
import com.github.kuya32.vintracker.destinations.SignUpScreenDestination
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = mediumSpace)
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_car),
                        contentDescription = "Car",
                        modifier = Modifier
                            .offset(0.dp, 26.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.Black,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(64.dp))
                StandardTextField(
                    text = emailState.text,
                    onValueChange = {
                        viewModel.onEvent(LoginEvent.EnteredEmail(it))
                    },
                    label = stringResource(id = R.string.email),
                    hint = "",
                    leadingIcon = Icons.Default.Email,
                    error = when (emailState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.email_required)
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
                        viewModel.onEvent(LoginEvent.EnteredPassword(it))
                    },
                    label = stringResource(id = R.string.password),
                    hint = "",
                    leadingIcon = Icons.Default.Lock,
                    error = when (passwordState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.password_required)
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
                    isPasswordVisible = passwordState.isPasswordVisible,
                    onPasswordToggleClick = {
                        viewModel.onEvent(LoginEvent.ToggledPasswordVisibility)
                    }
                )
                Spacer(modifier = Modifier.height(mediumSpace))
                Button(
                    onClick = { navigator.navigate(MainAppScreenDestination) },
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
                    contentPadding = PaddingValues(mediumSpace),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = mediumSpace)
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(smallSpace))
                    Icon(
                        imageVector = Icons.Default.Login,
                        contentDescription = stringResource(id = R.string.login_icon)
                    )
                }
                Spacer(modifier = Modifier.height(mediumSpace))
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(bottom = mediumSpace)
                        .clickable {
                            navigator.navigate(ForgotPasswordScreenDestination)
                        }
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.dont_have_an_account),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(smallSpace))
            Text(
                text = stringResource(id = R.string.sign_up),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navigator.navigate(SignUpScreenDestination)
                    }
            )
        }
    }

}