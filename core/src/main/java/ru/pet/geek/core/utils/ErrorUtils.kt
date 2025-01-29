package ru.pet.geek.core.utils

fun dependencyProviderError(dependencyName: String = "Dependency"): Nothing {
    error("The application context you have passed does not implement $dependencyName")
}

fun viewModelStoreProvidedError(): Nothing {
    error("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner")
}