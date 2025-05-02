package manuelklyukvin.contacts_app.build_src.gradle_plugins

import manuelklyukvin.contacts_app.build_src.modules.coreDomain
import manuelklyukvin.contacts_app.build_src.plugins.Plugins
import org.gradle.api.Project

class DataGradlePlugin : CoreGradlePlugin() {
    override fun applyPlugins(project: Project) {
        project.apply { plugin(Plugins.JVM) }
    }

    override fun applyDependencies(project: Project) {
        project.dependencies.apply {
            coreDomain()
        }
    }
}