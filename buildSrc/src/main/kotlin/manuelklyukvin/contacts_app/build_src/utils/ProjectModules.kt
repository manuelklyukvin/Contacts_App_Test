package manuelklyukvin.contacts_app.build_src.utils

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

internal fun DependencyHandler.presentationModule(module: String) = implementation(project(module + PRESENTATION))
internal fun DependencyHandler.domainModule(module: String) = implementation(project(module + DOMAIN))
internal fun DependencyHandler.dataModule(module: String) = implementation(project(module + DATA))
internal fun DependencyHandler.diModule(module: String) = implementation(project(module + DI))

private const val PRESENTATION = ":presentation"
private const val DOMAIN = ":domain"
private const val DATA = ":data"
private const val DI = ":di"