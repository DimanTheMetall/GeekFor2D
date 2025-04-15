package ru.pet.geek.mappers

import ru.pet.geek.UiText
import ru.pet.geek.domain.entities.dto.AuthorModel
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.dto.FavoriteState
import ru.pet.geek.domain.entities.dto.GenreModel
import ru.pet.geek.domain.entities.dto.enums.CurrentContentType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.entities.FavoriteStatusUi
import ru.pet.geek.widgets.BadgeListWidgetDataUi
import ru.pet.geek.widgets.BadgeListWidgetDataUiImpl
import ru.pet.geek.widgets.BadgeWidgetDataUi
import ru.pet.geek.widgets.BadgeWidgetDataUiImpl
import ru.pet.geek.widgets.ContentHolderInfoUi
import ru.pet.geek.widgets.ContentHolderInfoUiImpl
import ru.pet.geek.widgets.ContentInfoUi
import ru.pet.geek.widgets.ContentInfoUiImpl
import ru.pet.geek.widgets.FavoriteButtonInfoUi
import ru.pet.geek.widgets.FavoriteButtonInfoUiImpl
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
        title = UiText.ResourcesText(ru.pet.geek.ui.R.string.ui_genres),
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
        title = UiText.ResourcesText(ru.pet.geek.ui.R.string.ui_authors),
    )

fun EntryModel.toHorisontalCorouselContentHodler(
    onImageClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    favoriteState: FavoriteState,
): ContentHolderInfoUi =
    ContentHolderInfoUiImpl(
        onClick = onImageClick,
        imageUrl = this.imagesModel.webp.imageUrl,
        contentInfo =
            toContentInfo(
                isFavorite = favoriteState,
                onFavoriteButtonCLick = onFavoriteButtonClick,
            ),
    )

fun EntryModel.toContentInfo(
    isFavorite: FavoriteState,
    onFavoriteButtonCLick: () -> Unit,
): ContentInfoUi =
    ContentInfoUiImpl(
        title = title,
        favoriteStatusInfo =
            isFavorite.getFavoriteButtonUiState(
                onClick = onFavoriteButtonCLick,
            ),
    )

fun FavoriteState.getFavoriteButtonUiState(onClick: () -> Unit): FavoriteButtonInfoUi =
    FavoriteButtonInfoUiImpl(
        onClick = onClick,
        status = if (isFavorite) FavoriteStatusUi.FAVORITE else FavoriteStatusUi.UNFAVORITE,
    )
