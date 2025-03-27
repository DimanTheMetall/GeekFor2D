package ru.pet.geek.mappers

import ru.pet.geek.domain.entities.dto.enums.ContentType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.widgets.StatusWidgetInfo

fun ContentType.toUi(default: ContentTypeUi = ContentTypeUi.Manga): ContentTypeUi =
    when (this) {
        ContentType.Manga -> ContentTypeUi.Manga
        ContentType.Unknown -> default
    }

fun Status?.toUi(): StatusWidgetInfo =
    when (this) {
        Status.Finished -> StatusWidgetInfo.Finished
        Status.Publishing -> StatusWidgetInfo.Publishing
        null -> StatusWidgetInfo.Unknown
    }
