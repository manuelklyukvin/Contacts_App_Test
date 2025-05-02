package manuelklyukvin.contacts_app.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import manuelklyukvin.contacts_app.core.R
import manuelklyukvin.contacts_app.core.ui.navigation.NavigationState
import manuelklyukvin.contacts_app.core.ui.navigation.rememberNavigationState
import manuelklyukvin.contacts_app.core.ui.theme.resources.Fonts

val LocalNavigationState = staticCompositionLocalOf<NavigationState> { error("No NavigationState provided") }
private val localColorScheme = staticCompositionLocalOf<AppColorScheme> { error("No AppColorScheme provided") }
private val localShapes = staticCompositionLocalOf<AppShapes> { error("No AppShapes provided") }
private val localTypography = staticCompositionLocalOf<AppTypography> { error("No AppTypography provided") }

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colorScheme = AppColorScheme(
        primary = colorResource(R.color.primary),
        onPrimary = colorResource(R.color.on_primary),
        secondary = colorResource(R.color.secondary),
        onSecondary = colorResource(R.color.on_secondary),
        error = colorResource(R.color.error),
        background = colorResource(R.color.background),
        onBackground = colorResource(R.color.on_background),
        surface = colorResource(R.color.surface),
        onSurface = colorResource(R.color.on_surface),
        outline = colorResource(R.color.outline)
    )

    val typography = AppTypography(
        label = TextStyle(
            fontFamily = Fonts.ptSansCaption,
            color = colorScheme.onSurface,
            fontSize = 12.sp
        ),
        body = TextStyle(
            fontFamily = Fonts.ptSansCaption,
            color = colorScheme.onSurface,
            fontSize = 14.sp
        ),
        title = TextStyle(
            fontFamily = Fonts.ptSansCaption,
            color = colorScheme.onSurface,
            fontSize = 16.sp
        ),
        headline = TextStyle(
            fontFamily = Fonts.ptSansCaption,
            color = colorScheme.onSurface,
            fontSize = 20.sp
        )
    )

    val shapes = AppShapes(
        screenPadding = 12.dp,
        roundedCornerShape = RoundedCornerShape(12.dp),

        paddingExtraSmall = 4.dp,
        paddingSmall = 8.dp,
        paddingMedium = 12.dp,
        paddingLarge = 16.dp,
        paddingExtraLarge = 20.dp,

        sizeExtraSmall = 16.dp,
        sizeSmall = 24.dp,
        sizeMedium = 32.dp,
        sizeLarge = 48.dp,
        sizeExtraLarge = 64.dp
    )

    CompositionLocalProvider(
        LocalNavigationState provides rememberNavigationState(),
        localColorScheme provides colorScheme,
        localShapes provides shapes,
        localTypography provides typography,
        content = content
    )
}

object AppTheme {
    val colorScheme @Composable get() = localColorScheme.current
    val shapes @Composable get() = localShapes.current
    val typography @Composable get() = localTypography.current
}