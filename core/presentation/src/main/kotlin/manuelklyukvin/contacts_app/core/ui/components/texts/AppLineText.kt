package manuelklyukvin.contacts_app.core.ui.components.texts

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme

@Composable
fun AppLineText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = AppTheme.typography.body,
    color: Color = style.color,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
        maxLines = 1,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview
@Composable
private fun LightAppLineTextPreview() {
    AppLineTextPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkAppLineTextPreview() {
    AppLineTextPreview()
}

@Composable
private fun AppLineTextPreview() {
    AppTheme {
        Surface(color = AppTheme.colorScheme.surface) {
            AppLineText(text = "Preview")
        }
    }
}