package com.example.entities

sealed class ProcessState<out T : Any> {
    data class Error(val throwable: Throwable) : ProcessState<Nothing>()

    data class Success<out T : Any>(val result: T) : ProcessState<T>()
}
