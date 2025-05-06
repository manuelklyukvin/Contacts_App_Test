import manuelklyukvin.contacts_app.build_src.configs.GradleNamespaces
import manuelklyukvin.contacts_app.build_src.gradle_plugins.InfrastructureGradlePlugin
import manuelklyukvin.contacts_app.build_src.modules.mainData
import manuelklyukvin.contacts_app.build_src.modules.mainDomain

apply<InfrastructureGradlePlugin>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = GradleNamespaces.MAIN
}

dependencies {
    mainDomain()
    mainData()
}