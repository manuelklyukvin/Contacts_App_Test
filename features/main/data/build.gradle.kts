import manuelklyukvin.contacts_app.build_src.configs.GradleNamespaces
import manuelklyukvin.contacts_app.build_src.gradle_plugins.DataGradlePlugin
import manuelklyukvin.contacts_app.build_src.modules.mainDomain

apply<DataGradlePlugin>()

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = GradleNamespaces.MAIN
}

dependencies {
    mainDomain()
}