import manuelklyukvin.contacts_app.build_src.configs.GradleNamespaces
import manuelklyukvin.contacts_app.build_src.gradle_plugins.DiGradlePlugin
import manuelklyukvin.contacts_app.build_src.modules.mainData
import manuelklyukvin.contacts_app.build_src.modules.mainDomain
import manuelklyukvin.contacts_app.build_src.modules.mainPresentation

apply<DiGradlePlugin>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = GradleNamespaces.MAIN
}

dependencies {
    mainPresentation()
    mainDomain()
    mainData()
}