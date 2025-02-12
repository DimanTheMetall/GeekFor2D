plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KSP)
    kotlin(Plugins.KOTLINX_SERIALIZATION)
}

android {
    namespace = "${MetaInfo.APP_PACK}.${Modules.ENTITIES}"
}
