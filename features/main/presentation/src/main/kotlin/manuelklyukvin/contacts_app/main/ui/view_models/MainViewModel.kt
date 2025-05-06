package manuelklyukvin.contacts_app.main.ui.view_models

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import manuelklyukvin.contacts_app.core.ui.view_models.CoreViewModel
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreViewState
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.contacts.use_cases.GetContactGroupsUseCase
import manuelklyukvin.contacts_app.main.models.toPresentation
import manuelklyukvin.contacts_app.main.phone.use_cases.MakeCallUseCase
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainIntent
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainState

class MainViewModel(
    private val getContactGroupsUseCase: GetContactGroupsUseCase,
    private val makeCallUseCase: MakeCallUseCase
) : CoreViewModel<MainState, MainIntent>(MainState()) {
    private var loadContactGroupsJob: Job? = null

    override fun onIntent(intent: MainIntent) = when (intent) {
        MainIntent.OnScreenOpened -> onScreenOpened()
        is MainIntent.OnContactClicked -> onContactClicked(intent.phoneNumber)
        MainIntent.OnRetryButtonClicked -> onRetryButtonClicked()
    }

    private fun onScreenOpened() = withInitialState { loadContactGroups() }

    private fun onContactClicked(phoneNumber: String) = withContentState {
        val makeCallResult = makeCallUseCase(phoneNumber)
        if (makeCallResult is OperationResult.Error) {
            reduce { copy(viewState = CoreViewState.ERROR, error = makeCallResult.error) }
        }
    }

    private fun onRetryButtonClicked() = withErrorState { loadContactGroups() }

    private fun loadContactGroups() {
        reduce { copy(viewState = CoreViewState.LOADING) }
        loadContactGroupsJob?.cancel()

        loadContactGroupsJob = viewModelScope.launch {
            when (val getContactGroupsResult = getContactGroupsUseCase()) {
                is OperationResult.Success -> reduce {
                    copy(
                        viewState = CoreViewState.CONTENT,
                        contactGroups = getContactGroupsResult.data.map { it.toPresentation() },
                        error = null
                    )
                }
                is OperationResult.Error -> reduce {
                    copy(viewState = CoreViewState.ERROR, error = getContactGroupsResult.error)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        loadContactGroupsJob?.cancel()
    }
}