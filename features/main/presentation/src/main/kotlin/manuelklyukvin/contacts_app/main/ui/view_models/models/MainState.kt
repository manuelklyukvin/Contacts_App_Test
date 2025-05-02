package manuelklyukvin.contacts_app.main.ui.view_models.models

import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreState
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreViewState
import manuelklyukvin.contacts_app.main.models.PresentationContact

data class MainState(
    override val viewState: CoreViewState = CoreViewState.INITIAL,
    val contacts: List<PresentationContact> = emptyList(),
    val error: String? = null
) : CoreState()