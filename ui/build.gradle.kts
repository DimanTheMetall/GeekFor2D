plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    id(Plugins.COMPOSE_COMPILER)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.UI}"

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(Dependency.APP_COMPAT)

    implementation(Dependency.COMPOSE_UI)
    implementation(Dependency.COMPOSE_UI_UTILS)
    implementation(Dependency.COMPOSE_RUNTIME)
    implementation(Dependency.COMPOSE_TOOLING)
    implementation(Dependency.COMPOSE_ANIMATION)
    implementation(Dependency.COMPOSE_MATERIAL_3)
    implementation(Dependency.COMPOSE_MATERIAL_3_WINDOW_SIZE)
    implementation(Dependency.COIL_COMPOSE)
    implementation(Dependency.OKHTTP)
}
