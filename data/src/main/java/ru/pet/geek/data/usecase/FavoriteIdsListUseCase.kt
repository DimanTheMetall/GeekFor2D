package ru.pet.geek.data.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.pet.geek.data.repository.MangaRepository

class FavoriteIdsListUseCase(
    val mangaRepository: MangaRepository,
) {
    //TODO: implement
    suspend fun execute(): Flow<List<Int>> = flowOf(emptyList())
}
