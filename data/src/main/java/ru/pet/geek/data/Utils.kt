package ru.pet.geek.data

import retrofit2.Response
import ru.pet.geek.core.LocalResponse

fun <IN, OUT> Response<IN>.toLocalResponse(map: IN.() -> OUT?): LocalResponse<OUT> {
    val data = this.body()?.map()
    // Check empty list
    if (data is Collection<*> && data.isEmpty()) return LocalResponse.Error(EmptyDataException())
    return if (isSuccessful && data != null) {
        LocalResponse.Success(data)
    } else {
        LocalResponse.Error(EmptyDataException())
    }
}

class EmptyDataException : Exception("No data from response")
