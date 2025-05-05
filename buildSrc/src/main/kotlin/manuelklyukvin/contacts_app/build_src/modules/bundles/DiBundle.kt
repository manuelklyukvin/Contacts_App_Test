package manuelklyukvin.contacts_app.build_src.modules.bundles

import manuelklyukvin.contacts_app.build_src.modules.coreDi
import manuelklyukvin.contacts_app.build_src.modules.mainDi
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.diBundle() {
     coreDi()
     mainDi()
}