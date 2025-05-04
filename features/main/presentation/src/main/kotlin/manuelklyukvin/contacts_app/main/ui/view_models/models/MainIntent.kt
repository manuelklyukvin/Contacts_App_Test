package manuelklyukvin.contacts_app.main.ui.view_models.models

import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreIntent

sealed class MainIntent : CoreIntent() {
    data object OnScreenOpened : MainIntent()
    data class OnContactClicked(val phoneNumber: String) : MainIntent()
    data object OnRetryButtonClicked : MainIntent()
}