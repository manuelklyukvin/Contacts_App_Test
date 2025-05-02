import manuelklyukvin.contacts_app.build_src.configs.GradleNamespaces
import manuelklyukvin.contacts_app.build_src.configs.GradleVersions
import manuelklyukvin.contacts_app.build_src.dependencies.androidx
import manuelklyukvin.contacts_app.build_src.dependencies.coil
import manuelklyukvin.contacts_app.build_src.dependencies.koin
import manuelklyukvin.contacts_app.build_src.dependencies.splashScreen
import manuelklyukvin.contacts_app.build_src.modules.bundles.diBundle
import manuelklyukvin.contacts_app.build_src.modules.bundles.presentationBundle

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
}

android {
    namespace = GradleNamespaces.APP
    compileSdk = GradleVersions.COMPILE_SDK

    defaultConfig {
        applicationId = GradleNamespaces.APP
        minSdk = GradleVersions.MIN_SDK
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    koin()
    coil()
    splashScreen()

    presentationBundle()
    diBundle()
}