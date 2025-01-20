package ru.pet.geek.utils

inline fun <reified T> providedError(): Nothing {
    error("No ${T::class.simpleName} provided")
}