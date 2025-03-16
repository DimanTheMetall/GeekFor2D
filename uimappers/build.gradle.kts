plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    id(Plugins.COMPOSE_COMPILER)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.UI_MAPPERS}"

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(Dependency.APP_COMPAT)

    implementation(Dependency.COMPOSE_UI)

    implementation(project(Modules.getModulesDependency(Modules.CORE)))
    implementation(project(Modules.getModulesDependency(Modules.UI)))
}