package ru.pet.geek.utils

inline fun <reified T> compositionProviderError(): Nothing {
    error("No ${T::class.simpleName} provided")
}