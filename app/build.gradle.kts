import src.Dependency
import src.MetaInfo

plugins {
    id(Plugins.APP)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.COMPOSE_COMPILER)
    id(Plugins.KSP)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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
    implementation(Dependency.CORE_KTX)
    implementation(Dependency.ACTIVITY_COMPOSE)
    implementation(Dependency.COMPOSE_UI)
    implementation(Dependency.COMPOSE_UI_UTILS)
    implementation(Dependency.COMPOSE_RUNTIME)
    implementation(Dependency.COMPOSE_TOOLING)
    implementation(Dependency.COMPOSE_ANIMATION)
    implementation(Dependency.COMPOSE_MATERIAL_3)
    implementation(Dependency.COMPOSE_MATERIAL_3_WINDOW_SIZE)
    implementation(Dependency.COMPOSE_MATERIAL_3_NAV_SUIT)
}