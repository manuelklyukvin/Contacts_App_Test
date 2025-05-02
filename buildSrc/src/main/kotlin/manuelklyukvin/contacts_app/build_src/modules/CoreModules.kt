package manuelklyukvin.contacts_app.build_src.modules

import manuelklyukvin.contacts_app.build_src.utils.projectModule
import org.gradle.api.artifacts.dsl.DependencyHandler

private const val MODULE = ":core"

fun DependencyHandler.corePresentation() = projectModule("$MODULE:presentation")
fun DependencyHandler.coreDomain() = projectModule("$MODULE:domain")