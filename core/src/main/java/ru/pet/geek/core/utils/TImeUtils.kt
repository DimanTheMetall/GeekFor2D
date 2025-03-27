package ru.pet.geek.core.utils

import org.joda.time.DateTime
import java.util.Locale

@Suppress("ktlint:standard:function-naming")
fun String?.UTCtoUiFormat(): String {
    if (this == null) return ""

    val dateTime = DateTime(this)
    val date =
        dateTime
            .toLocalDate()
            .toString("dd  MMMM  yyyy", Locale("RU"))
    return date
}
