import com.android.build.gradle.internal.plugins.LibraryPlugin

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.APP) version Versions.AGP apply false
    id(Plugins.KOTLIN_ANDROID) version Versions.KOTLIN apply false
    id(Plugins.COMPOSE_COMPILER) version Versions.KOTLIN apply false
    id(Plugins.KSP) version Versions.KSP apply false
    id(Plugins.JVM) version Versions.KOTLIN
}

allprojects {
    plugins.matching { it is LibraryPlugin }.whenPluginAdded {
        configure<com.android.build.gradle.LibraryExtension> {
            compileSdk = MetaInfo.COMPILE_SDK

            compileOptions {
                sourceCompatibility = MetaInfo.JAVA_VERSION
                targetCompatibility = MetaInfo.JAVA_VERSION
                tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
                    compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_18)
                }
            }

        }


    }

    repositories {
        google()
        mavenCentral()
    }
}