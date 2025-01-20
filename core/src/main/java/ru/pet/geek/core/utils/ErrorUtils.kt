package ru.pet.geek.core.utils

inline fun <reified T> providedError(): Nothing {
    error("No ${T::class.simpleName} provided")
}