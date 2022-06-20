package com.github.kuya32.vintracker.feature_auth.presentation.forgot_password

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph
@Destination
@Composable
fun ForgotPasswordScreen(
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val forgotPasswordState = viewModel.forgotPasswordState.value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(mediumSpace)
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = largeSpace, horizontal = mediumSpace)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_forgot_password_icon),
                    contentDescription = stringResource(id = R.string.forgot_password_image),
                    modifier = Modifier
                        .size(150.dp)
                )
                Spacer(modifier = Modifier.height(mediumSpace))
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(smallSpace))
                Text(
                    text = stringResource(id = R.string.enter_email_associated_account),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(largeSpace))
                StandardTextField(
                    text = emailState.text,
                    onValueChange = {
                        viewModel.onEvent(ForgotPasswordEvent.EnteredEmail(it))
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
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(mediumSpace))
                Button(
                    onClick = {
                              // TODO: Navigate to the check email for verification screen
                    },
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary),
                    contentPadding = PaddingValues(mediumSpace),
                    modifier = Modifier
                        .padding(end = mediumSpace)
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