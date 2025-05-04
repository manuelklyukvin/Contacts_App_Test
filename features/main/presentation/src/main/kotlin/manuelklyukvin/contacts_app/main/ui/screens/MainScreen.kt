package manuelklyukvin.contacts_app.main.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import manuelklyukvin.contacts_app.core.ui.screens.ErrorScreen
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreViewState
import manuelklyukvin.contacts_app.main.ui.view_models.MainViewModel
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainIntent
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onIntent = viewModel::onIntent

    LaunchedEffect(Unit) {
        onIntent(MainIntent.OnScreenOpened)
    }

    Log.d("MainViewState", state.viewState.toString())

    when (state.viewState) {
        CoreViewState.INITIAL -> Unit
        CoreViewState.LOADING -> Unit
        CoreViewState.CONTENT -> ContentMainScreen(state, onIntent)
        CoreViewState.ERROR -> ErrorScreen(
            error = state.error,
            onRetryButtonClicked = { onIntent(MainIntent.OnRetryButtonClicked) }
        )
    }
}