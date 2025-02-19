package ru.pet.geek.core

sealed interface GeneralState<out T> {

    class Loading : GeneralState<Nothing> {
        override fun equals(other: Any?): Boolean {
            return when {
                other == null -> false
                else -> javaClass == javaClass
            }
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    data class Error(val e: Throwable) : GeneralState<Nothing>

    data class Success<out T>(val data: T) : GeneralState<T>

    val dataIfSuccess: T?
        get() = if (this is Success) data else null
}