package com.github.kuya32.vintracker

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph
@NavGraph
annotation class AppNavGraph(
    val start: Boolean = false
)
