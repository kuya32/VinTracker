package com.github.kuya32.vintracker.core.utils

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val uiText: UiText? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(data: T? = null, uiText: UiText?): Resource<T>(data, uiText)
}
