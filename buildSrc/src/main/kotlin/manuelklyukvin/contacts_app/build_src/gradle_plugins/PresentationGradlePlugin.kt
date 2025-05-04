package manuelklyukvin.contacts_app.build_src.gradle_plugins

import manuelklyukvin.contacts_app.build_src.configs.GradleVersions
import manuelklyukvin.contacts_app.build_src.dependencies.androidx
import manuelklyukvin.contacts_app.build_src.dependencies.koin
import manuelklyukvin.contacts_app.build_src.modules.coreDomain
import manuelklyukvin.contacts_app.build_src.modules.corePresentation
import manuelklyukvin.contacts_app.build_src.plugins.Plugins
import manuelklyukvin.contacts_app.build_src.utils.android
import org.gradle.api.Project

class PresentationGradlePlugin : CoreGradlePlugin() {
    override fun applyPlugins(project: Project) {
        project.apply {
            plugin(Plugins.ANDROID_LIBRARY)
            plugin(Plugins.KOTLIN)
            plugin(Plugins.COMPOSE)
        }
    }

    override fun applyProjectConfigs(project: Project) {
        project.android().apply {
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
    }

    override fun applyDependencies(project: Project) {
        project.dependencies.apply {
            androidx()
            koin()

            corePresentation()
            coreDomain()
        }
    }
}