package com.github.kuya32.vintracker.feature_auth.presentation.forgot_password

sealed class ForgotPasswordEvent {
    data class EnteredEmail(val value: String): ForgotPasswordEvent()
    object Submit: ForgotPasswordEvent()
}
