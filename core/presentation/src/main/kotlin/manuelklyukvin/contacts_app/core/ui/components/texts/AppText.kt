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
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = AppTheme.typography.body,
    color: Color = style.color,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
        maxLines = maxLines,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview
@Composable
private fun LightAppTextPreview() {
    AppTextPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkAppTextPreview() {
    AppTextPreview()
}

@Composable
private fun AppTextPreview() {
    AppTheme {
        Surface(color = AppTheme.colorScheme.surface) {
            AppText(text = "Preview")
        }
    }
}