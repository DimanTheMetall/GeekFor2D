package ru.pet.geek.core

sealed interface GeneralState<out T> {

    data object Loading : GeneralState<Nothing>

    data class Error(val e: Throwable) : GeneralState<Nothing>

    data class Success<out T>(val data: T) : GeneralState<T>

    val dataIfSuccess: T?
        get() = if (this is Success) data else null
}