package ru.pet.geek.mappers

import ru.pet.geek.domain.entities.dto.enums.CurrentContentType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.widgets.StatusWidgetInfo

fun CurrentContentType.toUi(): ContentTypeUi =
    when (this) {
        CurrentContentType.Manga -> ContentTypeUi.Manga
        CurrentContentType.Anime -> ContentTypeUi.Anime
        CurrentContentType.Character -> ContentTypeUi.Characters
    }

fun Status?.toUi(): StatusWidgetInfo =
    when (this) {
        Status.Finished -> StatusWidgetInfo.Finished
        Status.Publishing -> StatusWidgetInfo.Publishing
        Status.OnHiatus -> StatusWidgetInfo.OnHiatus
        Status.Discontinued -> StatusWidgetInfo.Discontinued
        Status.NotYetPublished -> StatusWidgetInfo.NotYetPublished
        null -> StatusWidgetInfo.Unknown
    }
