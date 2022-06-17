package com.github.kuya32.vintracker.core.domain.states

import com.github.kuya32.vintracker.core.utils.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
