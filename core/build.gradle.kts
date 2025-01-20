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
    implementation(Dependency.COMPOSE_RUNTIME)
}