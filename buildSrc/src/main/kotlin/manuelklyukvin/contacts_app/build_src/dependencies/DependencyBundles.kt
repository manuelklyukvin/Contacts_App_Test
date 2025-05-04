package manuelklyukvin.contacts_app.build_src.dependencies

import manuelklyukvin.contacts_app.build_src.utils.implementation
import manuelklyukvin.contacts_app.build_src.utils.testImplementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidx() {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(platform(Dependencies.COMPOSE_BOM))
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.UI)
    implementation(Dependencies.UI_GRAPHICS)
    implementation(Dependencies.UI_TOOLING)
    implementation(Dependencies.UI_TOOLING_PREVIEW)
}

fun DependencyHandler.koin() {
    implementation(Dependencies.KOIN_CORE)
    implementation(Dependencies.KOIN_ANDROID)
    implementation(Dependencies.KOIN_COMPOSE)
}

fun DependencyHandler.serialization() = implementation(Dependencies.SERIALIZATION)

fun DependencyHandler.navigation() = implementation(Dependencies.NAVIGATION)

fun DependencyHandler.coroutines() = implementation(Dependencies.COROUTINES)

fun DependencyHandler.coil() = implementation(Dependencies.COIL)

fun DependencyHandler.splashScreen() = implementation(Dependencies.SPLASH_SCREEN)

fun DependencyHandler.permissions() = implementation(Dependencies.PERMISSIONS)

fun DependencyHandler.testing() {
    testImplementation(Dependencies.JUNIT)
    testImplementation(Dependencies.MOCKK)
}