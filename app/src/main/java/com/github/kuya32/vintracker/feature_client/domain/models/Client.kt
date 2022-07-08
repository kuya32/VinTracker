package com.github.kuya32.vintracker.feature_client.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Client(
    val fullName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val address: String = "",
    val license: String = "",
    val licenseImageUrl: String = "",
    val notes: String = ""
): Parcelable
