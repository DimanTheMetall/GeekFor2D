package ru.pet.geek.core.mappers

import ru.pet.geek.core.GeneralState
import ru.pet.geek.core.LocalResponse

@Suppress("UNCHECKED_CAST")
fun <T> LocalResponse<T>.toGeneralState(): GeneralState<T> =
    when (this) {
        is LocalResponse.Error -> GeneralState.Error(e = this.error)
        is LocalResponse.Success<*> -> GeneralState.Success(data = data as T)
    }

@Suppress("UNCHECKED_CAST")
fun <IN, OUT> GeneralState<IN>.map(map: (IN) -> OUT): GeneralState<OUT> =
    when (this) {
        is GeneralState.Error -> this
        is GeneralState.Loading -> this
        is GeneralState.Success<*> -> GeneralState.Success(data = map(data as IN))
    }
