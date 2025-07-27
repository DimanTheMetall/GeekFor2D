package ru.pet.geek.core.progress

import ru.pet.geek.core.progress.ProgressChangedState.SuccessComplete

fun interface ProgressListener<in PROGRESS, in RESULT> {
    fun onProgressChanged(newState: ProgressChangedState<PROGRESS, RESULT>)
}

sealed interface ProgressChangedState<out PROGRESS, out Result> {
    data class InProgress<T>(val progress: T) : ProgressChangedState<T, Nothing>
    data class Error(val e: Throwable) : ProgressChangedState<Nothing, Nothing>
    data class SuccessComplete<out R>(val result: R) : ProgressChangedState<Nothing, R>
}



fun <RESULT> ProgressChangedState<Any, RESULT>.onSuccessComplete(onSuccessComplete: (SuccessComplete<RESULT>) -> Unit) = when(this) {
    is ProgressChangedState.Error, is ProgressChangedState.InProgress<*> -> Unit
    is SuccessComplete<RESULT> -> onSuccessComplete.invoke(this)
}

fun ProgressChangedState<*, *>.onErrorComplete(onErrorComplete: (ProgressChangedState.Error) -> Unit) = when(this) {
    is ProgressChangedState.Error -> onErrorComplete.invoke(this)
    is SuccessComplete<*>, is ProgressChangedState.InProgress<*> -> Unit
}

fun <PROGRESS> ProgressChangedState<PROGRESS, *>.onProgressChanged(onProgressChanged: (ProgressChangedState.InProgress<PROGRESS>) -> Unit) = when(this) {
    is ProgressChangedState.Error, is SuccessComplete<*> -> Unit
    is ProgressChangedState.InProgress<PROGRESS> -> onProgressChanged.invoke(this)
}

fun ProgressChangedState<*, *>.onAnywayComplete(onAnywayComplete: () -> Unit) = when(this) {
    is ProgressChangedState.Error, is SuccessComplete<*> -> onAnywayComplete.invoke()
    is ProgressChangedState.InProgress<*> -> Unit
}