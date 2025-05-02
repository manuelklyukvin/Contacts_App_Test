package manuelklyukvin.contacts_app.main.ui.view_models

import manuelklyukvin.contacts_app.core.ui.view_models.CoreViewModel
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainIntent
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainState

class MainViewModel : CoreViewModel<MainState, MainIntent>(MainState()) {
    override fun onIntent(intent: MainIntent) = when (intent) {
        MainIntent.OnScreenOpened -> { }
    }
}