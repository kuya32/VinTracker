package com.github.kuya32.vintracker.core.presentation.navigation

import com.github.kuya32.vintracker.core.presentation.navigation.AppNavGraph
import com.ramcosta.composedestinations.annotation.NavGraph

@AppNavGraph
@NavGraph
annotation class SearchClientNavGraph(
    val start: Boolean = false
)