package manuelklyukvin.contacts_app.main.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme
import manuelklyukvin.contacts_app.main.ui.view_models.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.shapes.screenPadding)
    ) {

    }
}