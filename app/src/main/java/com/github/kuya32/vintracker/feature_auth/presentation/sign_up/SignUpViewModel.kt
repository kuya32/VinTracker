package com.github.kuya32.vintracker.feature_auth.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.kuya32.vintracker.core.domain.states.PasswordTextFieldState
import com.github.kuya32.vintracker.core.domain.states.StandardTextFieldState
import com.github.kuya32.vintracker.feature_auth.presentation.login.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    private val _firstNameState = mutableStateOf(StandardTextFieldState())
    val firstNameState: State<StandardTextFieldState> = _firstNameState

    private val _lastNameState = mutableStateOf(StandardTextFieldState())
    val lastNameState: State<StandardTextFieldState> = _lastNameState

    private val _phoneNumberState = mutableStateOf(StandardTextFieldState())
    val phoneNumberState: State<StandardTextFieldState> = _phoneNumberState

    private val _usernameState = mutableStateOf(StandardTextFieldState())
    val usernameState: State<StandardTextFieldState> = _usernameState

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _confirmPasswordState = mutableStateOf(PasswordTextFieldState())
    val confirmPasswordState: State<PasswordTextFieldState> = _confirmPasswordState

    private val _isBoxCheckedState = mutableStateOf(false)
    val isBoxCheckedState: State<Boolean> = _isBoxCheckedState

    private val _signUpState = mutableStateOf(SignUpState())
    val signUpState: State<SignUpState> = _signUpState

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
             is SignUpEvent.EnteredPhoneNumber -> {
                 _phoneNumberState.value = _phoneNumberState.value.copy(
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
             is SignUpEvent.ToggledPasswordVisibility -> {
                 _passwordState.value = _passwordState.value.copy(
                     isPasswordVisible = !passwordState.value.isPasswordVisible
                 )
             }
             is SignUpEvent.EnteredConfirmPassword -> {
                 _confirmPasswordState.value = _confirmPasswordState.value.copy(
                     text = event.value
                 )
             }
             is SignUpEvent.ToggledConfirmationPasswordVisibility -> {
                 _confirmPasswordState.value = _confirmPasswordState.value.copy(
                     isPasswordVisible = !confirmPasswordState.value.isPasswordVisible
                 )
             }
             is SignUpEvent.ToggledCheckBox -> {
                 _isBoxCheckedState.value = !isBoxCheckedState.value
             }
             is SignUpEvent.SignUp -> {
                 signUp()
             }
         }
    }

    private fun signUp() {

    }
}