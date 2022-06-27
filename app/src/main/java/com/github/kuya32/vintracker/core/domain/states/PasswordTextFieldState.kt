package com.github.kuya32.vintracker.core.domain.states

import com.github.kuya32.vintracker.core.utils.Error

data class PasswordTextFieldState(
    val text: String = "",
    val error: Error? = null,
    val isPasswordVisible: Boolean = false
)
