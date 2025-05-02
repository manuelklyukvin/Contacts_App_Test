import manuelklyukvin.contacts_app.build_src.gradle_plugins.DataGradlePlugin
import manuelklyukvin.contacts_app.build_src.modules.mainDomain

apply<DataGradlePlugin>()

dependencies {
    mainDomain()
}