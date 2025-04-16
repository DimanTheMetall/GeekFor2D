package ru.pet.geek.domain.entities.dto

import ru.pet.geek.domain.entities.interfaces.IdHolder

data class EntryModel(
    override val malId: Int,
    val title: String,
    val imagesModel: ImagesModel,
) : IdHolder
