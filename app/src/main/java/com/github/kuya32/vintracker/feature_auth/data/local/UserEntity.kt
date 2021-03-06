package com.github.kuya32.vintracker.feature_auth.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val firstName: String,
    val lastName: String,
    val accountImageUrl: String,
    val phoneNumber: String,
    val username: String,
    val email: String,
    val password: String,
    @PrimaryKey val id: Int? = null
)
