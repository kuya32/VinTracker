package com.github.kuya32.vintracker.feature_auth.presentation.sign_up

import com.github.kuya32.vintracker.feature_auth.presentation.login.LoginEvent

sealed class SignUpEvent{
    data class EnteredFirstName(val value: String): SignUpEvent()
    data class EnteredLastName(val value: String): SignUpEvent()
    data class EnteredUsername(val value: String): SignUpEvent()
    data class EnteredEmail(val value: String): SignUpEvent()
    data class EnteredPassword(val value: String): SignUpEvent()
    data class EnteredConfirmPassword(val value: String): SignUpEvent()
    object ToggledPasswordVisibility: SignUpEvent()
    object ToggledConfirmationPasswordVisibility: SignUpEvent()
    object ToggledCheckBox: SignUpEvent()
    object SignUp: SignUpEvent()
}
