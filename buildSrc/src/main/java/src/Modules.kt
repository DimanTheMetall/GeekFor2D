object Modules {
    const val CORE = "core"
    const val UI = "ui"
    const val NAVIGATION_CONTROLLER = "navigationController"
    const val FEED = "feed"
    const val FAVORITE = "favorite"
    const val DATA = "data"
    const val ENTITIES = "entities"
    const val BASE_CARDS = "basecards"
    const val CARDS = "cards"
    const val RANDOM_MANGA = "randommanga"
    const val FEATURES = "features"
    const val UI_MAPPERS = "uimappers"

    fun getModulesDependency(vararg strings: String) = strings.asList().joinToString(separator = "") { ":$it" }
}

object Packages {
    const val FEATURES = "features"
    const val DOMAIN = "domain"
}
