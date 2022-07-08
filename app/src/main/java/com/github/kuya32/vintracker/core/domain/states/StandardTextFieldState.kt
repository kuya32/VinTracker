package com.github.kuya32.vintracker.core.domain.states

import com.github.kuya32.vintracker.core.utils.Error

data class StandardTextFieldState(
    var text: String = "",
    val error: Error? = null
)
