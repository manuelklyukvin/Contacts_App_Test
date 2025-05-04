import manuelklyukvin.contacts_app.build_src.configs.GradleNamespaces
import manuelklyukvin.contacts_app.build_src.configs.GradleVersions
import manuelklyukvin.contacts_app.build_src.dependencies.androidx
import manuelklyukvin.contacts_app.build_src.dependencies.coil
import manuelklyukvin.contacts_app.build_src.dependencies.navigation
import manuelklyukvin.contacts_app.build_src.dependencies.serialization
import manuelklyukvin.contacts_app.build_src.modules.coreDomain

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = GradleNamespaces.CORE
    compileSdk = GradleVersions.COMPILE_SDK

    defaultConfig {
        minSdk = GradleVersions.MIN_SDK
    }
    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = GradleVersions.JAVA
        targetCompatibility = GradleVersions.JAVA
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    androidx()
    serialization()
    navigation()
    coil()

    coreDomain()
}