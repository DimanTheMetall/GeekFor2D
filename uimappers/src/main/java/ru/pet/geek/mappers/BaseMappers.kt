package ru.pet.geek.mappers

import ru.pet.geek.UiText
import ru.pet.geek.domain.entities.dto.GenreModel
import ru.pet.geek.domain.entities.dto.enums.CurrentContentType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.widgets.BadgeWidgetDataUi
import ru.pet.geek.widgets.BadgeWidgetDataUiImpl
import ru.pet.geek.widgets.GenresListWidgetDataUi
import ru.pet.geek.widgets.GenresListWidgetDataUiImpl
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

fun GenreModel.toWidgetUiModel(onClick: () -> Unit = {}): BadgeWidgetDataUi =
    BadgeWidgetDataUiImpl(
        text = UiText.StringText(name),
        onClick = onClick,
    )

fun List<GenreModel>.toWidgetUiModels(): List<BadgeWidgetDataUi> = map { it.toWidgetUiModel() }

fun List<GenreModel>.toListGenresWidgetUi(
    isOpen: Boolean,
    onOpenClick: () -> Unit,
    onCloseClick: () -> Unit,
): GenresListWidgetDataUi =
    GenresListWidgetDataUiImpl(
        allGenres = this.toWidgetUiModels(),
        isOpen = isOpen,
        openWidgetClick = onOpenClick,
        closeWidgetClick = onCloseClick,
    )
