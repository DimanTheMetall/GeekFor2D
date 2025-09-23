package ru.pet.geek.core.utils

import android.content.Context
import java.io.File

class FileUtils(
    private val context: Context
) {

    companion object {
        const val IMAGE_FOLDER = "images"
        const val SEPARATOR = '/'
    }

    fun getAppPackage(): File {
        return context.filesDir
    }

    fun generatePath(list: List<String>): String {
        return list.joinToString(separator = SEPARATOR.toString())
    }

    fun getImagesPackages(): File {
        val pack = File(generatePath(list = listOf(getAppPackage().absolutePath, IMAGE_FOLDER)))
        if (pack.exists().not()) {
            pack.createNewFile()
        }

        return pack
    }


}