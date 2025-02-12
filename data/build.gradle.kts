plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    kotlin(Plugins.KOTLINX_SERIALIZATION)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.DATA}"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.COMPOSE_RUNTIME)
    implementation(Dependency.COROUTINES)
    implementation(Dependency.KOTLINX_SERIALIZATION)
    implementation(Dependency.RETROFIT)
    implementation(Dependency.OKHTTP)
    implementation(Dependency.OKHTTP_LOGGER_INTERCEPTOR)
    implementation(Dependency.KOTLINX_SERIALIZATION_CONVERTOR)

    implementation(Dependency.DAGGER)
    ksp(Dependency.DAGGER_COMPILER)

    implementation(project(Modules.getModulesDependency(Modules.CORE)))
}