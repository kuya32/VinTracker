package com.github.kuya32.vintracker.feature_auth.domain.use_case

import com.github.kuya32.vintracker.core.domain.utils.ValidationUtil
import com.github.kuya32.vintracker.feature_auth.domain.model.SignUpResult
import com.github.kuya32.vintracker.feature_auth.domain.repository.AuthRepository

class SignUpUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        username: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): SignUpResult {
        val phoneNumberError = ValidationUtil.validatePhoneNumber(phoneNumber)
        val usernameError = ValidationUtil.validateUsername(username)
        val emailError = ValidationUtil.validateEmail(email)
        val passwordError = ValidationUtil.validatePassword(password)
        val passwordConfirmationError = ValidationUtil.validatePasswordConfirmation(password, passwordConfirmation)

        if (emailError != null || usernameError != null || phoneNumberError != null || passwordError != null || passwordConfirmationError != null) {
            return SignUpResult(
                emailError = emailError,
                usernameError = usernameError,
                phoneNumberError = phoneNumberError,
                passwordError = passwordError,
                passwordConfirmationError = passwordConfirmationError
            )
        }

        val result = repository.signUp(
            firstName,
            lastName,
            phoneNumber,
            username,
            email,
            password
        )

        return SignUpResult(
            result = result
        )
    }
}