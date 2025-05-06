package manuelklyukvin.contacts_app.build_src.utils

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}
internal fun DependencyHandler.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}