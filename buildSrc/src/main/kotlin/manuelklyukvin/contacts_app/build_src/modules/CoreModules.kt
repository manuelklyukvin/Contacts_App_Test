package manuelklyukvin.contacts_app.build_src.modules

import manuelklyukvin.contacts_app.build_src.utils.dataModule
import manuelklyukvin.contacts_app.build_src.utils.diModule
import manuelklyukvin.contacts_app.build_src.utils.domainModule
import manuelklyukvin.contacts_app.build_src.utils.presentationModule
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.corePresentation() = presentationModule(MODULE)
fun DependencyHandler.coreDomain() = domainModule(MODULE)
fun DependencyHandler.coreData() = dataModule(MODULE)
fun DependencyHandler.coreDi() = diModule(MODULE)

private const val MODULE = ":core"