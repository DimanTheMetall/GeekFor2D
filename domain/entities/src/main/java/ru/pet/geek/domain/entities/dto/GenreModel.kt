package ru.pet.geek.domain.entities.dto

import ru.pet.geek.domain.entities.dto.enums.GenreType

data class GenreModel(
    val malId: Int,
    val name: String,
    val type: GenreType,
    val url: String?,
)
