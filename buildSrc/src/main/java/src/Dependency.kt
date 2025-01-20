package src

import Versions

object Dependency {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"

    //ui
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
    const val COMPOSE_UI_UTILS = "androidx.compose.ui:ui-util:${Versions.COMPOSE}"
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation:${Versions.COMPOSE}"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val COMPOSE_MATERIAL_3 = "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL_3}"
    const val COMPOSE_MATERIAL_3_NAV_SUIT = "androidx.compose.material3:material3-adaptive-navigation-suite:${Versions.COMPOSE_MATERIAL_3}"
    const val COMPOSE_MATERIAL_3_WINDOW_SIZE = "androidx.compose.material3:material3-window-size-class:${Versions.COMPOSE_MATERIAL_3}"

    //dagger
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"

}