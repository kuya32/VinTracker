package com.github.kuya32.vintracker.core.utils

import androidx.annotation.StringRes
import com.github.kuya32.vintracker.R

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    data class StringResource(@StringRes val id: Int): UiText()

    companion object {
        fun unknownError(): UiText {
            return UiText.StringResource(R.string.error_unknown)
        }
    }
}
