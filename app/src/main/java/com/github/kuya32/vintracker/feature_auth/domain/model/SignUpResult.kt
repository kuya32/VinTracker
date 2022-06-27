package com.github.kuya32.vintracker.feature_auth.domain.model

import com.github.kuya32.vintracker.core.utils.SimpleResource
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors

data class SignUpResult(
    val phoneNumberError: AuthErrors? = null,
    val usernameError: AuthErrors? = null,
    val emailError: AuthErrors? = null,
    val passwordError: AuthErrors? = null,
    val passwordConfirmationError: AuthErrors? = null,
    val result: SimpleResource? = null
)
