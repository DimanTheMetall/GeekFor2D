package ru.pet.geek.data.repository

import ru.pet.geek.core.fileintent.FileIntent
import ru.pet.geek.core.progress.BytesProgressListener
import ru.pet.geek.core.utils.FileUtils
import ru.pet.geek.data.local.FileLocalSource
import ru.pet.geek.data.remote.FileRemoteSource
import java.io.File

class FileRepositoryImpl(
    private val fileRemoteSource: FileRemoteSource,
    private val fileLocalSource: FileLocalSource,
    private val fileUtils: FileUtils,
) : FileRepository {

    override suspend fun execute(
        fileIntent: FileIntent,
        progressListener: BytesProgressListener<File>?
    ) {
        TODO("Not yet implemented")
    }
}