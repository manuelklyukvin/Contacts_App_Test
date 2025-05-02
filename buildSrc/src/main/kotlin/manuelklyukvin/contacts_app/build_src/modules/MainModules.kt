package manuelklyukvin.contacts_app.build_src.modules

import manuelklyukvin.contacts_app.build_src.utils.projectModule
import org.gradle.api.artifacts.dsl.DependencyHandler

private const val MODULE = ":features:main"

fun DependencyHandler.mainPresentation() = projectModule("$MODULE:presentation")
fun DependencyHandler.mainDomain() = projectModule("$MODULE:domain")
fun DependencyHandler.mainData() = projectModule("$MODULE:data")
fun DependencyHandler.mainDi() = projectModule("$MODULE:di")