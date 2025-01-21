object Modules {
    const val CORE = "core"
    const val UI = "ui"
    const val NAVIGATION_CONTROLLER = "navigationController"

    fun getModulesDependency(moduleName: String) = ":$moduleName"
}