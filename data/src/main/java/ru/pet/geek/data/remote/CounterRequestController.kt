package ru.pet.geek.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pet.geek.core.LocalResponse

class CounterRequestController : RequestsController {

    //TODO add counter
    override fun onRequest() = Unit

    //TODO add counter
    override fun startRequest(): Boolean = true

    override suspend fun <OUT> prepareRequest(request: suspend () -> LocalResponse<OUT>): LocalResponse<OUT> =
        withContext(
            Dispatchers.IO
        ) {
            try {
                if (startRequest()) {
                    onRequest()
                    return@withContext request.invoke()
                } else {
                    LocalResponse.Error(LimitRequestException())
                }
            } catch (e: Throwable) {
                return@withContext LocalResponse.Error(e)
            }
        }
}

class LimitRequestException: Exception("Limit of request")