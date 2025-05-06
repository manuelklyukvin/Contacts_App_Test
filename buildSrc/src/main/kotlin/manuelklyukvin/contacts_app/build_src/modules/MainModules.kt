package manuelklyukvin.contacts_app.build_src.modules

import manuelklyukvin.contacts_app.build_src.utils.dataModule
import manuelklyukvin.contacts_app.build_src.utils.diModule
import manuelklyukvin.contacts_app.build_src.utils.domainModule
import manuelklyukvin.contacts_app.build_src.utils.infrastructureModule
import manuelklyukvin.contacts_app.build_src.utils.presentationModule
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.mainPresentation() = presentationModule(MODULE)
fun DependencyHandler.mainDomain() = domainModule(MODULE)
fun DependencyHandler.mainData() = dataModule(MODULE)
fun DependencyHandler.mainInfrastructure() = infrastructureModule(MODULE)
fun DependencyHandler.mainDi() = diModule(MODULE)

private const val MODULE = ":features:main"