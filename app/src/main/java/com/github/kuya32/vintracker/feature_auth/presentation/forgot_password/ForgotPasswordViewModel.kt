package com.github.kuya32.vintracker.feature_auth.presentation.forgot_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.kuya32.vintracker.core.domain.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(): ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _forgotPasswordState = mutableStateOf(ForgotPasswordState())
    val forgotPasswordState: State<ForgotPasswordState> = _forgotPasswordState

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.EnteredEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.value
                )
            }
            is ForgotPasswordEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {

    }
}