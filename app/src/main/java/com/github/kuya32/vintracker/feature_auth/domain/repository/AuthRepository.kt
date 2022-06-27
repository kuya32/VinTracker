package com.github.kuya32.vintracker.feature_auth.domain.repository

import com.github.kuya32.vintracker.core.utils.SimpleResource

interface AuthRepository {

    suspend fun signUp(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        username: String,
        email: String,
        password: String
    ): SimpleResource

    suspend fun login(
        email: String,
        password: String
    ): SimpleResource
}