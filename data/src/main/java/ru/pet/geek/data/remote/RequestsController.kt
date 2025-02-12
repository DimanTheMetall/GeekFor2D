package ru.pet.geek.data.remote

import ru.pet.geek.core.LocalResponse

interface RequestsController {
    fun onRequest()

    fun startRequest(): Boolean

    suspend fun <OUT> prepareRequest(request: suspend () -> LocalResponse<OUT>): LocalResponse<OUT>
}
