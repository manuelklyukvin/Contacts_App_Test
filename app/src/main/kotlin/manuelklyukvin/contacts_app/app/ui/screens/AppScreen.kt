package manuelklyukvin.contacts_app.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import manuelklyukvin.contacts_app.core.ui.navigation.graphs.AppNavGraph
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme
import manuelklyukvin.contacts_app.main.ui.screens.MainScreen

@Composable
fun AppScreen() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                AppNavGraph(mainScreen = { MainScreen() })
            }
        }
    }
}