package manuelklyukvin.contacts_app.build_src.gradle_plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType
import manuelklyukvin.contacts_app.build_src.dependencies.testing

open class CoreGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugins(project)
        applyProjectConfigs(project)
        applyDependencies(project)
        applyTesting(project)
    }

    protected open fun applyPlugins(project: Project) = Unit

    protected open fun applyProjectConfigs(project: Project) = Unit

    protected open fun applyDependencies(project: Project) = Unit

    protected open fun applyTesting(project: Project) {
        project.tasks.withType<Test> { useJUnitPlatform() }
        project.dependencies.apply { testing() }
    }
}