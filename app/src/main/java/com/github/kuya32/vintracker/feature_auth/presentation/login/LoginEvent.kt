package com.github.kuya32.vintracker.feature_auth.presentation.login

sealed class LoginEvent {
    data class EnteredEmail(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()
    object ToggledPasswordVisibility: LoginEvent()
    object Login: LoginEvent()
}
