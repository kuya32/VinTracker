package com.github.kuya32.vintracker.feature_auth.domain.use_case

import com.github.kuya32.vintracker.feature_auth.domain.model.LoginResult
import com.github.kuya32.vintracker.feature_auth.domain.repository.AuthRepository
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult {
        val emailError = if (email.isBlank()) AuthErrors.FieldEmpty else null
        val passwordError = if (password.isBlank()) AuthErrors.FieldEmpty else null

        if (emailError != null || passwordError != null) {
            return LoginResult(
                emailError = emailError,
                passwordError = passwordError
            )
        }

        return LoginResult(
            result = repository.login(email, password)
        )
    }
}