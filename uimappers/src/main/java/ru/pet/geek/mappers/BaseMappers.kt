package ru.pet.geek.mappers

import ru.pet.geek.domain.entities.dto.enums.ContentType
import ru.pet.geek.entities.ContentTypeUi

fun ContentType.toUi(default: ContentTypeUi = ContentTypeUi.Manga): ContentTypeUi =
    when (this) {
        ContentType.Manga -> ContentTypeUi.Manga
        ContentType.Unknown -> default
    }
