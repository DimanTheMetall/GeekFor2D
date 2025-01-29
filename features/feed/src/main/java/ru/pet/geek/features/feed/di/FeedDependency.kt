package ru.pet.geek.features.feed.di

import ru.pet.geek.features.feed.api.FeedDataApi
import ru.pet.geek.features.feed.api.FeedNavApi

interface FeedDependency {
    val feedNavApi: FeedNavApi
    val feedDataApi: FeedDataApi
}