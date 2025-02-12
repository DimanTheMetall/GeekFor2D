object Modules {
    const val CORE = "core"
    const val UI = "ui"
    const val NAVIGATION_CONTROLLER = "navigationController"
    const val FEED = "feed"
    const val FAVORITE = "favorite"
    const val DATA = "data"
    const val ENTITIES = "entities"

    fun getModulesDependency(vararg strings: String) =
        strings.asList().joinToString(separator = "") { ":$it" }
}

object Packages {
    const val FEATURES = "features"
    const val DOMAIN = "domain"
}