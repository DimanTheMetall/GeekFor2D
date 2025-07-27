package ru.pet.geek.core.progress

fun interface BytesProgressListener<in RESULT>: ProgressListener<BytesProgress, RESULT>

data class BytesProgress(
    val total: Long,
    val current: Long,
)