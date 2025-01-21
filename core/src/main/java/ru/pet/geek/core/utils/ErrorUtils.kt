package ru.pet.geek.core.utils

fun dependencyProviderError(): Nothing {
    error("The application context you have passed does not implement Dependency")
}

fun viewModelStoreProvidedError(): Nothing {
    error("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner")
}