import manuelklyukvin.contacts_app.build_src.configs.GradleNamespaces
import manuelklyukvin.contacts_app.build_src.dependencies.permissions
import manuelklyukvin.contacts_app.build_src.gradle_plugins.PresentationGradlePlugin
import manuelklyukvin.contacts_app.build_src.modules.mainDomain

apply<PresentationGradlePlugin>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = GradleNamespaces.MAIN
}

dependencies {
    permissions()
    mainDomain()
}