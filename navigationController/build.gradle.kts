plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    id(Plugins.COMPOSE_COMPILER)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.NAVIGATION_CONTROLLER}"
}

dependencies {
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.COROUTINES)
    implementation(Dependency.COMPOSE_RUNTIME)
    implementation(Dependency.COMPOSE_NAVIGATION)

    implementation(project(Modules.getModulesDependency(Modules.CORE)))
}