package manuelklyukvin.contacts_app.build_src.modules.bundles

import manuelklyukvin.contacts_app.build_src.modules.corePresentation
import manuelklyukvin.contacts_app.build_src.modules.mainPresentation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.presentationBundle() {
    corePresentation()
    mainPresentation()
}