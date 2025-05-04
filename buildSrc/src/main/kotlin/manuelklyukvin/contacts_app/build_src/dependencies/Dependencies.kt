package manuelklyukvin.contacts_app.build_src.dependencies

internal object Dependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${DependencyVersions.CORE_KTX}"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.LIFECYCLE_RUNTIME_KTX}"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${DependencyVersions.ACTIVITY_COMPOSE}"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${DependencyVersions.COMPOSE_BOM}"
    const val MATERIAL = "androidx.compose.material3:material3:${DependencyVersions.MATERIAL}"
    const val UI = "androidx.compose.ui:ui"
    const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"

    const val KOIN_CORE = "io.insert-koin:koin-core:${DependencyVersions.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${DependencyVersions.KOIN}"
    const val KOIN_COMPOSE = "io.insert-koin:koin-androidx-compose:${DependencyVersions.KOIN}"

    const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${DependencyVersions.SERIALIZATION}"

    const val NAVIGATION = "androidx.navigation:navigation-compose:${DependencyVersions.NAVIGATION}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersions.COROUTINES}"

    const val COIL = "io.coil-kt:coil-compose:${DependencyVersions.COIL}"

    const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${DependencyVersions.SPLASH_SCREEN}"

    const val PERMISSIONS = "com.google.accompanist:accompanist-permissions:${DependencyVersions.PERMISSIONS}"

    const val MOCKK = "io.mockk:mockk:${DependencyVersions.MOCKK}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${DependencyVersions.COROUTINES}"
}