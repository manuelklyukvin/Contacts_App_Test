package manuelklyukvin.contacts_app.core.ui.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Main : Routes()
}