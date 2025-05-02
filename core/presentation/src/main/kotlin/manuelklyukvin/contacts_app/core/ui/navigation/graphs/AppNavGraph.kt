package manuelklyukvin.contacts_app.core.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import manuelklyukvin.contacts_app.core.ui.navigation.Routes
import manuelklyukvin.contacts_app.core.ui.theme.LocalNavigationState
import manuelklyukvin.contacts_app.core.ui.theme.resources.Animations

@Composable
fun AppNavGraph(mainScreen: @Composable () -> Unit) {
    NavHost(
        navController = LocalNavigationState.current.navController,
        startDestination = Routes.Main,
        enterTransition = Animations.enterTransition,
        popExitTransition = Animations.exitTransition
    ) {
        composable<Routes.Main> { mainScreen() }
    }
}