package com.github.kuya32.vintracker

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@AppNavGraph
@NavGraph
annotation class AddClientNavGraph(
    val start: Boolean = false
)
