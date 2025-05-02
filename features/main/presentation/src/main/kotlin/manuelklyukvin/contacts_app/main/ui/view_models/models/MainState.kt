package manuelklyukvin.contacts_app.main.ui.view_models.models

import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreState
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreViewState

data class MainState(
    override val viewState: CoreViewState = CoreViewState.INITIAL
) : CoreState()