package ru.pet.geek.mappers

import ru.pet.geek.domain.entities.dto.TitleModel
import ru.pet.geek.domain.entities.dto.enums.TitleType

fun List<TitleModel>.toUi(): String = find { it.type == TitleType.Default }?.title ?: firstOrNull()?.title ?: ""
