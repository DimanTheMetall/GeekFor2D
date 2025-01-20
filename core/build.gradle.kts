import src.Dependency
import src.MetaInfo

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    id(Plugins.COMPOSE_COMPILER)
}

android {
    namespace = "${MetaInfo.APP_PACK}.core"
}

dependencies {
    implementation(Dependency.COMPOSE_RUNTIME)
}