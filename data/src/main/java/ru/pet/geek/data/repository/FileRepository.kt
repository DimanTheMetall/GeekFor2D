package ru.pet.geek.data.repository

import ru.pet.geek.core.fileintent.FileIntent
import ru.pet.geek.core.progress.BytesProgressListener
import java.io.File

interface FileRepository {

    suspend fun execute(fileIntent: FileIntent, progressListener: BytesProgressListener<File>? = null)
}