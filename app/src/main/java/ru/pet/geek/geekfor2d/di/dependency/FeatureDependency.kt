package ru.pet.geek.geekfor2d.di.dependency

import ru.pet.geek.manga.di.MangaRandomCardDependencies
import ru.pet.geek.features.feed.di.FeedDependency
import ru.pet.geek.imagecard.di.ImageCardDependencies

interface FeatureDependency :
    FeedDependency,
    MangaRandomCardDependencies,
    ImageCardDependencies