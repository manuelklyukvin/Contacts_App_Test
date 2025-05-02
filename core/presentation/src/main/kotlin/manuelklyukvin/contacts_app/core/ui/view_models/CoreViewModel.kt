package manuelklyukvin.contacts_app.core.ui.view_models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreIntent
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreState
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreViewState

abstract class CoreViewModel<S : CoreState, I : CoreIntent>(initialState: S) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state get() = _state.asStateFlow()

    protected fun reduce(reducer: S.() -> S) = _state.update { it.reducer() }

    abstract fun onIntent(intent: I)

    protected inline fun withInitialState(block: () -> Unit) {
        if (state.value.viewState == CoreViewState.INITIAL) block()
    }
    protected inline fun withLoadingState(block: () -> Unit) {
        if (state.value.viewState == CoreViewState.LOADING) block()
    }
    protected inline fun withContentState(block: () -> Unit) {
        if (state.value.viewState == CoreViewState.CONTENT) block()
    }
    protected inline fun withErrorState(block: () -> Unit) {
        if (state.value.viewState == CoreViewState.ERROR) block()
    }
}