plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.DATA}"
}

dependencies {
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.COMPOSE_RUNTIME)
    implementation(Dependency.COROUTINES)
    implementation(Dependency.KOTLINX_SERIALIZATION)
    implementation(Dependency.RETROFIT)
    implementation(Dependency.OKHTTP)
}