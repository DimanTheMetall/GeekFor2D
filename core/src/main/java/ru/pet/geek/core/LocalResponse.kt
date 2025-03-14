package ru.pet.geek.core

sealed interface LocalResponse<out DATA> {

    data class Success<T>(
        val data: T,
    ) : LocalResponse<T>

    data class Error(
        val error: Throwable,
    ) : LocalResponse<Nothing>

    val nonSafeData: DATA
        get() =
            when (this) {
                is Error -> error("Response is Error")
                is Success -> this.data
            }

    val nonSafeError: Throwable
        get() =
            when (this) {
                is Error -> this.error
                is Success -> error("Response dont have error")
            }
}
