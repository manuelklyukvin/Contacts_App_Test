package manuelklyukvin.contacts_app.main.ui.screens

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import manuelklyukvin.contacts_app.core.ui.screens.ErrorScreen
import manuelklyukvin.contacts_app.core.ui.view_models.models.CoreViewState
import manuelklyukvin.contacts_app.main.R
import manuelklyukvin.contacts_app.main.ui.view_models.MainViewModel
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainIntent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onIntent = viewModel::onIntent

    val readContactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
    val callPhonePermissionState = rememberPermissionState(Manifest.permission.CALL_PHONE)

    var currentPermissionRequest by remember { mutableStateOf<PermissionState?>(null) }

    LaunchedEffect(readContactsPermissionState, callPhonePermissionState) {
        when {
            !readContactsPermissionState.status.isGranted -> {
                currentPermissionRequest = readContactsPermissionState
                readContactsPermissionState.launchPermissionRequest()
            }
            !callPhonePermissionState.status.isGranted -> {
                currentPermissionRequest = callPhonePermissionState
                callPhonePermissionState.launchPermissionRequest()
            }
            else -> onIntent(MainIntent.OnScreenOpened)
        }
    }

    LaunchedEffect(readContactsPermissionState.status, callPhonePermissionState.status) {
        if (currentPermissionRequest?.status?.isGranted == true) {
            when (currentPermissionRequest) {
                readContactsPermissionState -> if (!callPhonePermissionState.status.isGranted) {
                    currentPermissionRequest = callPhonePermissionState
                    callPhonePermissionState.launchPermissionRequest()
                }
                callPhonePermissionState -> onIntent(MainIntent.OnScreenOpened)
            }
        }
    }

    when {
        !readContactsPermissionState.status.isGranted -> ErrorScreen(
            error = stringResource(R.string.read_contacts_permission_error),
            onRetryButtonClicked = {
                currentPermissionRequest = readContactsPermissionState
                readContactsPermissionState.launchPermissionRequest()
            }
        )
        !callPhonePermissionState.status.isGranted -> ErrorScreen(
            error = stringResource(R.string.call_phone_permission_error),
            onRetryButtonClicked = {
                currentPermissionRequest = callPhonePermissionState
                callPhonePermissionState.launchPermissionRequest()
            }
        )
        else -> when (state.viewState) {
            CoreViewState.INITIAL -> Unit
            CoreViewState.LOADING -> LoadingMainScreen()
            CoreViewState.CONTENT -> ContentMainScreen(state, onIntent)
            CoreViewState.ERROR -> ErrorScreen(
                error = state.error,
                onRetryButtonClicked = { onIntent(MainIntent.OnRetryButtonClicked) }
            )
        }
    }
}