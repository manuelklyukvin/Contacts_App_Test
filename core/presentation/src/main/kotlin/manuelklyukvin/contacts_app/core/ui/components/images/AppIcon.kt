package manuelklyukvin.contacts_app.core.ui.components.images

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import manuelklyukvin.contacts_app.core.R
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    model: Painter,
    tint: Color = AppTheme.colorScheme.primary
) {
    Icon(
        modifier = modifier,
        painter = model,
        tint = tint,
        contentDescription = null
    )
}

@Preview
@Composable
private fun LightAppIconPreview() {
    AppIconPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkAppIconPreview() {
    AppIconPreview()
}

@Composable
private fun AppIconPreview() {
    AppTheme {
        AppIcon(model = painterResource(R.drawable.logo))
    }
}