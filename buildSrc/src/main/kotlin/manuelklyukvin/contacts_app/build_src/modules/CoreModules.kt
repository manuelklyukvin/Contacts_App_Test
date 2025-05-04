package manuelklyukvin.contacts_app.build_src.modules

import manuelklyukvin.contacts_app.build_src.utils.presentationModule
import manuelklyukvin.contacts_app.build_src.utils.domainModule
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.corePresentation() = presentationModule(MODULE)
fun DependencyHandler.coreDomain() = domainModule(MODULE)

private const val MODULE = ":core"