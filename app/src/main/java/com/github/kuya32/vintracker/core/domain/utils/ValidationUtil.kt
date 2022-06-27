package com.github.kuya32.vintracker.core.domain.utils

import android.util.Patterns
import com.github.kuya32.vintracker.core.utils.Constants
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors

object ValidationUtil {

    fun validateEmail(email: String): AuthErrors? {
        val trimmedEmail = email.trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AuthErrors.InvalidEmail
        }
        if (trimmedEmail.isBlank()) {
            return AuthErrors.FieldEmpty
        }
        return null
    }

    fun validatePhoneNumber(phoneNumber: String): AuthErrors? {
        if (phoneNumber.length != 10) {
            return AuthErrors.InvalidPhoneNumber
        }
        if (phoneNumber.isBlank()) {
            return AuthErrors.FieldEmpty
        }
        return null
    }

    fun validateUsername(username: String): AuthErrors? {
        val trimmedUsername = username.trim()
        if (trimmedUsername.length < Constants.MIN_USERNAME_LENGTH) {
            return AuthErrors.InputTooShort
        }
        if (trimmedUsername.isBlank()) {
            return AuthErrors.FieldEmpty
        }
        return null
    }

    fun validatePassword(password: String): AuthErrors? {
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword) {
            return AuthErrors.InvalidPassword
        }
        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            return AuthErrors.InputTooShort
        }
        if (password.isBlank()) {
            return AuthErrors.FieldEmpty
        }
        return null
    }

    fun validatePasswordConfirmation(password: String, passwordConfirmation: String): AuthErrors? {
        if (passwordConfirmation != password) {
            return AuthErrors.PasswordDoesNotMatch
        }
        if (passwordConfirmation.isBlank()) {
            return AuthErrors.FieldEmpty
        }
        return null
    }
}