package manuelklyukvin.contacts_app.build_src.utils

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

internal fun DependencyHandler.projectModule(name: String) = implementation(project(name))