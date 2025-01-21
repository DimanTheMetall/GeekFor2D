plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    id(Plugins.COMPOSE_COMPILER)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.CORE}"
}

dependencies {
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.COMPOSE_RUNTIME)
    implementation(Dependency.COROUTINES)
    implementation(Dependency.LIFECYCLE_VIEWMODEL)
    implementation(Dependency.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Dependency.LIFECYCLE_VIEW_MODEL_COMPOSE)
}