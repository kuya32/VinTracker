package com.github.kuya32.vintracker

import com.ramcosta.composedestinations.annotation.NavGraph

@AppNavGraph
@NavGraph
annotation class SearchClientNavGraph(
    val start: Boolean = false
)