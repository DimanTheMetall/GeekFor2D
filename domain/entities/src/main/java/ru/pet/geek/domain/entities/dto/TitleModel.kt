package ru.pet.geek.domain.entities.dto

import ru.pet.geek.domain.entities.dto.enums.TitleType

data class TitleModel(
    val type: TitleType,
    val title: String
)
