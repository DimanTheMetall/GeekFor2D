package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.core.fileintent.FileIntent
import ru.pet.geek.core.fileintent.downloadToTempFolder
import ru.pet.geek.core.fileintent.renameToImageFile
import ru.pet.geek.core.progress.PercentProgressListener
import ru.pet.geek.core.progress.PercentToBytesProgressListenerAdapter
import ru.pet.geek.data.repository.FileRepository
import ru.pet.geek.imagecard.api.ImageCardDataApi
import ru.pet.geek.imagecard.api.ImageCardNavApi
import ru.pet.geek.navigationcontroller.NavigationControllerApi
import java.io.File

@Module
class ImageCardModule {

    @Provides
    fun provideImageCardNavApi(navController: NavigationControllerApi): ImageCardNavApi =
        object : ImageCardNavApi {
            override fun goBack() = navController.back()
        }

    @Provides
    fun provideImageCardDataApi(fileRepository: FileRepository): ImageCardDataApi =
        object : ImageCardDataApi {
            override suspend fun downloadAndGetImage(
                uri: String,
                contentId: Int,
                progressListener: PercentProgressListener<File>?,
            ) {
                val intentAction = FileIntent.Builder()
                    .renameToImageFile(id = contentId)
                    .downloadToTempFolder(url = uri)
                    .build()

                val percentProgressListener = progressListener?.let { PercentToBytesProgressListenerAdapter<File>()
                    .adapt(from = progressListener) }

                fileRepository.execute(fileIntent = intentAction, progressListener = percentProgressListener)
            }

        }
}