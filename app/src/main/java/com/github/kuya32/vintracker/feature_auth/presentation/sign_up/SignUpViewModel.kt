package com.github.kuya32.vintracker.feature_auth.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.kuya32.vintracker.core.domain.states.PasswordTextFieldState
import com.github.kuya32.vintracker.core.domain.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    private val _firstNameState = mutableStateOf(StandardTextFieldState())
    val firstNameState: State<StandardTextFieldState> = _firstNameState

    private val _lastNameState = mutableStateOf(StandardTextFieldState())
    val lastNameState: State<StandardTextFieldState> = _lastNameState

    private val _usernameState = mutableStateOf(StandardTextFieldState())
    val usernameState: State<StandardTextFieldState> = _usernameState

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _confirmPasswordState = mutableStateOf(PasswordTextFieldState())
    val confirmPasswordState: State<PasswordTextFieldState> = _confirmPasswordState

    fun onEvent(event: SignUpEvent) {
         when (event) {
             is SignUpEvent.EnteredFirstName -> {
                _firstNameState.value = _firstNameState.value.copy(
                    text = event.value
                )
             }
             is SignUpEvent.EnteredLastName -> {
                _lastNameState.value = _lastNameState.value.copy(
                    text = event.value
                )
             }
             is SignUpEvent.EnteredUsername -> {
                 _usernameState.value = _usernameState.value.copy(
                     text = event.value
                 )
             }
             is SignUpEvent.EnteredEmail -> {
                 _emailState.value = _emailState.value.copy(
                     text = event.value
                 )
             }
             is SignUpEvent.EnteredPassword -> {
                 _passwordState.value = _passwordState.value.copy(
                     text = event.value
                 )
             }
             is SignUpEvent.EnteredConfirmPassword -> {
                 _confirmPasswordState.value = _confirmPasswordState.value.copy(
                     text = event.value
                 )
             }
             is SignUpEvent.SignUp -> {
                 signUp()
             }
         }
    }

    private fun signUp() {

    }
}