package ru.pet.geek.data.mappers

import ru.pet.geek.data.remote.responses.GetRandomMangaResponse
import ru.pet.geek.data.remote.responses.inner.ContentTypeNet
import ru.pet.geek.data.remote.responses.inner.InnerImageResponse
import ru.pet.geek.data.remote.responses.inner.InnerImagesResponse
import ru.pet.geek.data.remote.responses.inner.InnerPublishedDateModel
import ru.pet.geek.data.remote.responses.inner.InnerTitleModel
import ru.pet.geek.data.remote.responses.inner.StatusNet
import ru.pet.geek.data.remote.responses.inner.TitleTypeNet
import ru.pet.geek.domain.entities.dto.ImageModel
import ru.pet.geek.domain.entities.dto.ImagesModel
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel
import ru.pet.geek.domain.entities.dto.PublishingDateModel
import ru.pet.geek.domain.entities.dto.TitleModel
import ru.pet.geek.domain.entities.dto.enums.ContentType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.domain.entities.dto.enums.TitleType

internal fun GetRandomMangaResponse.toAppModel(): MangaRandomCardModel? {
    return MangaRandomCardModel(
        malId = malId ?: return null,
        url = url,
        images = images?.toAppModel() ?: ImagesModel(),
        approved = approved,
        titles = titles.mapNotNull { it.toAppModel() },
        title = title ?: return null,
        titleEnglish = titleEnglish,
        titleJapanese = titleJapanese,
        type = type?.toApp() ?: ContentType.Manga,
        chapters = chapters,
        volumes = volumes,
        status = status?.toAppModel(),
        score = score,
        scoredBy = scoredBy,
        publishedModel = published?.toAppModel(),
    )
}

internal fun InnerPublishedDateModel.toAppModel(): PublishingDateModel? {
    if (from == null && to == null) return null
    return PublishingDateModel(
        from = from,
        to = to,
    )
}

internal fun InnerImagesResponse.toAppModel(): ImagesModel =
    ImagesModel(
        jpg = jpg?.toAppModel() ?: ImageModel(),
        webp = webp?.toAppModel() ?: ImageModel(),
    )

internal fun InnerImageResponse.toAppModel(): ImageModel =
    ImageModel(
        imageUrl = imageUrl ?: "",
        smallImageUrl = smallImageUrl ?: "",
        largeImageUrl = largeImageUrl ?: "",
    )

internal fun InnerTitleModel.toAppModel(): TitleModel? {
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

internal fun ContentTypeNet.toApp() =
    when (this) {
        ContentTypeNet.Manga -> ContentType.Manga
        ContentTypeNet.Unknown -> ContentType.Unknown
    }

internal fun StatusNet.toAppModel() =
    when (this) {
        StatusNet.Finished -> Status.Finished
        StatusNet.Publishing -> Status.Publishing
    }
