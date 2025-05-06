package manuelklyukvin.contacts_app.build_src.utils

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

internal fun DependencyHandler.presentationModule(module: String) = implementation(project("$module:presentation"))
internal fun DependencyHandler.domainModule(module: String) = implementation(project("$module:domain"))
internal fun DependencyHandler.dataModule(module: String) = implementation(project("$module:data"))
internal fun DependencyHandler.infrastructureModule(module: String) = implementation(project("$module:infrastructure"))
internal fun DependencyHandler.diModule(module: String) = implementation(project("$module:di"))