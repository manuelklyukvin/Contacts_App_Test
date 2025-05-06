package manuelklyukvin.contacts_app.build_src.modules

import manuelklyukvin.contacts_app.build_src.utils.diModule
import manuelklyukvin.contacts_app.build_src.utils.domainModule
import manuelklyukvin.contacts_app.build_src.utils.infrastructureModule
import manuelklyukvin.contacts_app.build_src.utils.presentationModule
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.corePresentation() = presentationModule(MODULE)
fun DependencyHandler.coreDomain() = domainModule(MODULE)
fun DependencyHandler.coreInfrastructure() = infrastructureModule(MODULE)
fun DependencyHandler.coreDi() = diModule(MODULE)

private const val MODULE = ":core"