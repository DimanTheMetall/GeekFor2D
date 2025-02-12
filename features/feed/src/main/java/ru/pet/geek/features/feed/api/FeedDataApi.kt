package ru.pet.geek.features.feed.api

import ru.pet.geek.core.LocalResponse
import ru.pet.geek.domain.entities.dto.TestAzaza

interface FeedDataApi {

    suspend fun getRandomContent(): LocalResponse<TestAzaza>
}