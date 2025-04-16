package ru.pet.geek.data.mappers

import ru.pet.geek.data.remote.responses.GetRandomMangaResponse
import ru.pet.geek.data.remote.responses.inner.AuthorTypeNet
import ru.pet.geek.data.remote.responses.inner.ContentTypeMangaNet
import ru.pet.geek.data.remote.responses.inner.EntryModelNet
import ru.pet.geek.data.remote.responses.inner.GenreTypeNet
import ru.pet.geek.data.remote.responses.inner.InnerAuthorModelNet
import ru.pet.geek.data.remote.responses.inner.InnerGenreModelNet
import ru.pet.geek.data.remote.responses.inner.InnerImageNet
import ru.pet.geek.data.remote.responses.inner.InnerImagesNet
import ru.pet.geek.data.remote.responses.inner.InnerPublishedDateModelNet
import ru.pet.geek.data.remote.responses.inner.InnerTitleModelNet
import ru.pet.geek.data.remote.responses.inner.StatusNet
import ru.pet.geek.data.remote.responses.inner.TitleTypeNet
import ru.pet.geek.domain.entities.dto.AuthorModel
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.dto.GenreModel
import ru.pet.geek.domain.entities.dto.ImageModel
import ru.pet.geek.domain.entities.dto.ImagesModel
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel
import ru.pet.geek.domain.entities.dto.PublishingDateModel
import ru.pet.geek.domain.entities.dto.TitleModel
import ru.pet.geek.domain.entities.dto.enums.AuthorType
import ru.pet.geek.domain.entities.dto.enums.ContentTypeManga
import ru.pet.geek.domain.entities.dto.enums.GenreType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.domain.entities.dto.enums.TitleType

internal fun GetRandomMangaResponse.toAppModel(): MangaRandomCardModel? {
    return MangaRandomCardModel(
        malId = malId ?: return null,
        url = url,
        images = images?.toAppModel() ?: ImagesModel(),
        approved = approved,
        titles = titles.mapNotNull { it.toAppModel() },
        currentType = type?.toApp() ?: ContentTypeManga.Manga,
        chapters = chapters,
        volumes = volumes,
        status = status?.toAppModel(),
        score = score,
        scoredBy = scoredBy,
        publishedModel = published?.toAppModel(),
        synopsis = synopsis,
        genres = genres.mapNotNull { it.toAppModel(defaultType = GenreType.Manga) },
        authors = authors.mapNotNull { it.toAppModel(defaultType = AuthorType.People) },
    )
}

internal fun InnerPublishedDateModelNet.toAppModel(): PublishingDateModel? {
    if (from == null && to == null) return null
    return PublishingDateModel(
        from = from,
        to = to,
    )
}

internal fun InnerImagesNet.toAppModel(): ImagesModel =
    ImagesModel(
        jpg = jpg?.toAppModel() ?: ImageModel(),
        webp = webp?.toAppModel() ?: ImageModel(),
    )

internal fun InnerImageNet.toAppModel(): ImageModel =
    ImageModel(
        imageUrl = imageUrl ?: "",
        smallImageUrl = smallImageUrl ?: "",
        largeImageUrl = largeImageUrl ?: "",
    )

internal fun InnerTitleModelNet.toAppModel(): TitleModel? {
    return TitleModel(
        title = title ?: return null,
        type = type?.toAppModel() ?: TitleType.Unknown,
    )
}

internal fun TitleTypeNet.toAppModel() =
    when (this) {
        TitleTypeNet.Default -> TitleType.Default
        TitleTypeNet.Synonym -> TitleType.Synonym
        TitleTypeNet.Japanese -> TitleType.Japanese
        TitleTypeNet.Unknown -> TitleType.Unknown
    }

internal fun ContentTypeMangaNet.toApp() =
    when (this) {
        ContentTypeMangaNet.Manga -> ContentTypeManga.Manga
        ContentTypeMangaNet.Unknown -> ContentTypeManga.Unknown
        ContentTypeMangaNet.Manhua -> ContentTypeManga.Manhua
        ContentTypeMangaNet.Manhwa -> ContentTypeManga.Manhwa
        ContentTypeMangaNet.Novel -> ContentTypeManga.Novel
        ContentTypeMangaNet.OneShot -> ContentTypeManga.OneShot
        ContentTypeMangaNet.Doujinshi -> ContentTypeManga.Doujinshi
        ContentTypeMangaNet.OEL -> ContentTypeManga.OEL
        ContentTypeMangaNet.LightNovel -> ContentTypeManga.LightNovel
    }

internal fun StatusNet.toAppModel() =
    when (this) {
        StatusNet.Finished -> Status.Finished
        StatusNet.Publishing -> Status.Publishing
        StatusNet.OnHiatus -> Status.OnHiatus
        StatusNet.Discontinued -> Status.Discontinued
        StatusNet.NotYetPublished -> Status.NotYetPublished
    }

internal fun InnerGenreModelNet.toAppModel(defaultType: GenreType): GenreModel? {
    return GenreModel(
        malId = malId ?: return null,
        name = name ?: "",
        type = type?.toAppModel() ?: defaultType,
        url = url,
    )
}

internal fun GenreTypeNet.toAppModel(): GenreType =
    when (this) {
        GenreTypeNet.Manga -> GenreType.Manga
        GenreTypeNet.Anime -> GenreType.Anime
    }

internal fun AuthorTypeNet.toAppModel(): AuthorType =
    when (this) {
        AuthorTypeNet.People -> AuthorType.People
    }

internal fun InnerAuthorModelNet.toAppModel(defaultType: AuthorType): AuthorModel? {
    return AuthorModel(
        malId = malId ?: return null,
        name = name ?: "",
        type = type?.toAppModel() ?: defaultType,
        url = url,
    )
}

internal fun EntryModelNet.toAppModel(): EntryModel? {
    return EntryModel(
        malId = malId ?: return null,
        title = title ?: "",
        imagesModel = images?.toAppModel() ?: ImagesModel(),
    )
}

