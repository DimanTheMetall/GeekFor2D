plugins {
    id(Plugins.APP)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.COMPOSE_COMPILER)
    id(Plugins.KSP)
    kotlin(Plugins.KOTLINX_SERIALIZATION)
}

android {
    namespace = "ru.pet.geek.geekfor2d"
    compileSdk = MetaInfo.COMPILE_SDK

    defaultConfig {
        applicationId = MetaInfo.APP_ID
        minSdk = MetaInfo.MIN_SDK
        targetSdk = MetaInfo.TARGET_SDK
        versionCode = MetaInfo.VERSION_CODE
        versionName = MetaInfo.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = MetaInfo.JAVA_VERSION
        targetCompatibility = MetaInfo.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = MetaInfo.JVM_TARGET_STR
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILE_EXTENSIONS
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Dependency region
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.APP_COMPAT)

    implementation(Dependency.ACTIVITY_COMPOSE)
    implementation(Dependency.COMPOSE_UI)
    implementation(Dependency.COMPOSE_UI_UTILS)
    implementation(Dependency.COMPOSE_RUNTIME)
    implementation(Dependency.COMPOSE_TOOLING)
    implementation(Dependency.COMPOSE_ANIMATION)
    implementation(Dependency.COMPOSE_MATERIAL_3)
    implementation(Dependency.COMPOSE_MATERIAL_3_WINDOW_SIZE)
    implementation(Dependency.COMPOSE_NAVIGATION)

    implementation(Dependency.COROUTINES)
    implementation(Dependency.KOTLINX_SERIALIZATION)
    implementation(Dependency.KOTLINX_SERIALIZATION_CONVERTOR)

    implementation(Dependency.LIFECYCLE_VIEWMODEL)
    implementation(Dependency.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Dependency.LIFECYCLE_VIEW_MODEL_COMPOSE)


    implementation(Dependency.RETROFIT)
    implementation(Dependency.OKHTTP)
    implementation(Dependency.OKHTTP_LOGGER_INTERCEPTOR)


    implementation(Dependency.DAGGER)
    ksp(Dependency.DAGGER_COMPILER)

    //Modules region
    implementation(project(Modules.getModulesDependency(Modules.CORE)))
    implementation(project(Modules.getModulesDependency(Modules.UI)))
    implementation(project(Modules.getModulesDependency(Modules.NAVIGATION_CONTROLLER)))
    implementation(project(Modules.getModulesDependency(Modules.DATA)))
    implementation(project(Modules.getModulesDependency(Packages.FEATURES, Modules.FEED)))
    implementation(project(Modules.getModulesDependency(Packages.FEATURES, Modules.FAVORITE)))
}