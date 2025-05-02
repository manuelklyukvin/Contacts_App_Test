plugins {
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.serialization) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}