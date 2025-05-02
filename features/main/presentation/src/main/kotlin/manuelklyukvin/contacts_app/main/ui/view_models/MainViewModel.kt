package manuelklyukvin.contacts_app.main.ui.view_models

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import manuelklyukvin.contacts_app.core.ui.view_models.CoreViewModel
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreViewState
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.models.toPresentation
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainIntent
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainState
import manuelklyukvin.contacts_app.main.use_cases.GetContactsUseCase

class MainViewModel(
    private val getContactsUseCase: GetContactsUseCase
) : CoreViewModel<MainState, MainIntent>(MainState()) {
    private var loadContactsJob: Job? = null

    override fun onIntent(intent: MainIntent) = when (intent) {
        MainIntent.OnScreenOpened -> onScreenOpened()
        MainIntent.OnContactClicked -> onContactClicked()
        MainIntent.OnRetryButtonClicked -> onRetryButtonClicked()
    }

    private fun loadContacts() {
        reduce { copy(viewState = CoreViewState.LOADING) }
        loadContactsJob?.cancel()

        loadContactsJob = viewModelScope.launch {
            when (val getContactsResult = getContactsUseCase()) {
                is OperationResult.Success -> reduce {
                    copy(
                        viewState = CoreViewState.CONTENT,
                        contacts = getContactsResult.data.map { it.toPresentation() },
                        error = null
                    )
                }
                is OperationResult.Error -> reduce {
                    copy(
                        viewState = CoreViewState.ERROR,
                        error = getContactsResult.error
                    )
                }
            }
        }
    }

    private fun onScreenOpened() = withInitialState { loadContacts() }

    private fun onContactClicked() = withContentState { }

    private fun onRetryButtonClicked() = withErrorState { loadContacts() }

    override fun onCleared() {
        super.onCleared()
        loadContactsJob?.cancel()
    }
}