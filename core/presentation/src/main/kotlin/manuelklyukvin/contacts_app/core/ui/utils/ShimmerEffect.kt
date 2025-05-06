package manuelklyukvin.contacts_app.core.ui.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val shimmerTransition by transition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000))
    )

    val shimmerColors = listOf(
        AppTheme.colorScheme.surface,
        AppTheme.colorScheme.background,
        AppTheme.colorScheme.surface
    )

    graphicsLayer().drawWithCache {
        val widthPx = size.width
        val gradientWidth = widthPx / 2f

        val startX = shimmerTransition * widthPx
        val endX = startX + gradientWidth

        val shimmerBrush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(startX, 0f),
            end = Offset(endX, 0f)
        )

        onDrawWithContent {
            drawContent()
            drawRect(brush = shimmerBrush)
        }
    }
}