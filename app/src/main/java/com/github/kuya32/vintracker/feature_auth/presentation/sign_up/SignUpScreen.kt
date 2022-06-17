package com.github.kuya32.vintracker.feature_auth.presentation.sign_up

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@Destination
@Composable
fun SignUpScreen() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(text = "This is the sign up screen!")
    }
}