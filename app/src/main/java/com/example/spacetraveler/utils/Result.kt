package com.example.spacetraveler.utils

sealed class Result<T> {
    class OnLoading<T>: Result<T>()
    data class OnSuccess<T>(val data: T): Result<T>()
    data class OnError<T>(val throwable: Throwable): Result<T>()
}