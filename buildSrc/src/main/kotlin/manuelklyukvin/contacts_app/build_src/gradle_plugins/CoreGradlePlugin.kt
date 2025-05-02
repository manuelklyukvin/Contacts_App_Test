package manuelklyukvin.contacts_app.build_src.gradle_plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

open class CoreGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        applyPlugins(project)
        applyProjectConfigs(project)
        applyDependencies(project)
    }

    protected open fun applyPlugins(project: Project) = Unit
    protected open fun applyProjectConfigs(project: Project) = Unit
    protected open fun applyDependencies(project: Project) = Unit
}