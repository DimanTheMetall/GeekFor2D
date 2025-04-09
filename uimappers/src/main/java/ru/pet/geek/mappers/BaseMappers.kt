package ru.pet.geek.mappers

import ru.pet.geek.UiText
import ru.pet.geek.domain.entities.dto.AuthorModel
import ru.pet.geek.domain.entities.dto.GenreModel
import ru.pet.geek.domain.entities.dto.enums.CurrentContentType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.widgets.BadgeListWidgetDataUi
import ru.pet.geek.widgets.BadgeListWidgetDataUiImpl
import ru.pet.geek.widgets.BadgeWidgetDataUi
import ru.pet.geek.widgets.BadgeWidgetDataUiImpl
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

fun GenreModel.toBadgeWidgetUiModel(onClick: () -> Unit = {}): BadgeWidgetDataUi =
    BadgeWidgetDataUiImpl(
        text = UiText.StringText(name),
        onClick = onClick,
    )

fun AuthorModel.toWidgetUiModel(onClick: () -> Unit = {}): BadgeWidgetDataUi =
    BadgeWidgetDataUiImpl(
        text = UiText.StringText(name),
        onClick = onClick,
    )

fun List<GenreModel>.genresToBadgeWidgetUiModels(): List<BadgeWidgetDataUi> = map { it.toBadgeWidgetUiModel() }

fun List<AuthorModel>.authorsToBadgeWidgetUiModels(): List<BadgeWidgetDataUi> = map { it.toWidgetUiModel() }

fun List<GenreModel>.genresToListBadgesWidgetUi(
    isOpen: Boolean,
    onOpenClick: () -> Unit,
    onCloseClick: () -> Unit,
): BadgeListWidgetDataUi =
    BadgeListWidgetDataUiImpl(
        allGenres = this.genresToBadgeWidgetUiModels(),
        isOpen = isOpen,
        openWidgetClick = onOpenClick,
        closeWidgetClick = onCloseClick,
    )

fun List<AuthorModel>.authorsToListBadgesWidgetUi(
    isOpen: Boolean,
    onOpenClick: () -> Unit,
    onCloseClick: () -> Unit,
): BadgeListWidgetDataUi =
    BadgeListWidgetDataUiImpl(
        allGenres = this.authorsToBadgeWidgetUiModels(),
        isOpen = isOpen,
        openWidgetClick = onOpenClick,
        closeWidgetClick = onCloseClick,
    )
