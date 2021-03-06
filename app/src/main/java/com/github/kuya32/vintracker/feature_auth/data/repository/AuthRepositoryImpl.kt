package com.github.kuya32.vintracker.feature_auth.data.repository

import com.github.kuya32.vintracker.core.utils.SimpleResource
import com.github.kuya32.vintracker.feature_auth.data.remote.AuthApi
import com.github.kuya32.vintracker.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
): AuthRepository {

    override suspend fun signUp(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        username: String,
        email: String,
        password: String
    ): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        TODO("Not yet implemented")
    }
}