object Modules {
    const val CORE = "ui"
    const val UI = "core"

    fun getModulesDependency(moduleName: String) = ":$moduleName"
}