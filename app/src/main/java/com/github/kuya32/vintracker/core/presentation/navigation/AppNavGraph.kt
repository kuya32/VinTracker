package com.github.kuya32.vintracker.core.presentation.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@NavGraph
annotation class AppNavGraph(
    val start: Boolean = false
)
