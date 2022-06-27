package com.github.kuya32.vintracker.feature_auth.presentation.utils

import com.github.kuya32.vintracker.core.utils.Error

sealed class AuthErrors: Error() {
    object FieldEmpty: AuthErrors()
    object InputTooShort: AuthErrors()
    object InvalidEmail: AuthErrors()
    object InvalidPassword: AuthErrors()
    object PasswordDoesNotMatch: AuthErrors()
    object InvalidPhoneNumber: AuthErrors()
}
