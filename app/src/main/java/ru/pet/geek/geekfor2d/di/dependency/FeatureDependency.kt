package ru.pet.geek.geekfor2d.di.dependency

import com.example.manga.di.MangaRandomCardDependencies
import ru.pet.geek.features.feed.di.FeedDependency

interface FeatureDependency :
    FeedDependency,
    MangaRandomCardDependencies
