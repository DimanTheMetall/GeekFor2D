package ru.pet.geek.domain.entities.dto

import ru.pet.geek.domain.entities.dto.enums.AuthorType

data class AuthorModel(
    val malId: Int,
    val name: String,
    val type: AuthorType,
    val url: String?,
)
