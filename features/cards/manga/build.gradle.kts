plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    id(Plugins.COMPOSE_COMPILER)
    kotlin(Plugins.KOTLINX_SERIALIZATION)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.BASE_CARDS}"

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
    implementation(Dependency.KOTLINX_SERIALIZATION)
    implementation(Dependency.ACTIVITY_COMPOSE)

    implementation(Dependency.LIFECYCLE_VIEWMODEL)
    implementation(Dependency.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Dependency.LIFECYCLE_VIEW_MODEL_COMPOSE)

    implementation(Dependency.DAGGER)
    ksp(Dependency.DAGGER_COMPILER)

    implementation(project(Modules.getModulesDependency(Modules.CORE)))
    implementation(project(Modules.getModulesDependency(Modules.UI)))
    implementation(project(Modules.getModulesDependency(Modules.FEATURES, Modules.CARDS, Modules.BASE_CARDS)))
}
