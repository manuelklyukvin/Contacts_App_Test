package manuelklyukvin.contacts_app.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(val navController: NavHostController) {
    fun navigateTo(route: Routes) = navController.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }

    fun navigateUp() = navController.popBackStack()
}

@Composable
fun rememberNavigationState(): NavigationState {
    val navController = rememberNavController()
    navController.enableOnBackPressed(true)
    return remember(navController) { NavigationState(navController) }
}