import manuelklyukvin.contacts_app.build_src.configs.GradleNamespaces
import manuelklyukvin.contacts_app.build_src.configs.GradleVersions
import manuelklyukvin.contacts_app.build_src.dependencies.koin
import manuelklyukvin.contacts_app.build_src.modules.coreDomain
import manuelklyukvin.contacts_app.build_src.modules.corePresentation

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = GradleNamespaces.CORE
    compileSdk = GradleVersions.COMPILE_SDK

    defaultConfig {
        minSdk = GradleVersions.MIN_SDK
    }
    compileOptions {
        sourceCompatibility = GradleVersions.JAVA
        targetCompatibility = GradleVersions.JAVA
    }
}

dependencies {
    koin()

    corePresentation()
    coreDomain()
}