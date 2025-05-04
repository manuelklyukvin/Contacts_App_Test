package manuelklyukvin.contacts_app.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import manuelklyukvin.contacts_app.core.ui.components.texts.AppLineText
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    areDefaultPaddingsEnabled: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = AppTheme.colorScheme.surface,
                shape = AppTheme.shapes.roundedCornerShape
            )
            .let {
                if (areDefaultPaddingsEnabled) {
                    it.padding(AppTheme.shapes.paddingMedium)
                } else {
                    it
                }
            },
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@Preview
@Composable
private fun LightAppCardPreview() {
    AppCardPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkAppCardPreview() {
    AppCardPreview()
}

@Composable
private fun AppCardPreview() {
    AppTheme {
        AppCard(areDefaultPaddingsEnabled = true) {
            AppLineText(text = "Preview")
        }
    }
}