package ru.pet.geek.imagecard.api

import ru.pet.geek.core.progress.PercentProgressListener
import java.io.File

interface ImageCardDataApi {

    suspend fun downloadAndGetImage(
        uri: String,
        contentId: Int,
        progressListener: PercentProgressListener<File>? = null
    )
}