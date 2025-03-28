package ru.pet.geek.entities

import ru.pet.geek.ui.R
import ru.pet.geek.utils.TextResHolder

enum class ContentTypeUi(
    override val textRes: Int,
) : TextResHolder {
    Manga(R.string.ui_manga),
    Anime(R.string.ui_anime),
    Characters(R.string.ui_character),
}
