package manuelklyukvin.contacts_app.core.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import manuelklyukvin.contacts_app.core.R
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    model: Any,
    contentScale: ContentScale = ContentScale.Fit
) {
    if (model is Painter) {
        Image(
            modifier = modifier,
            painter = model,
            contentScale = contentScale,
            contentDescription = null
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = model,
            contentScale = contentScale,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun AppIconPreview() {
    AppTheme {
        AppImage(model = painterResource(R.drawable.logo))
    }
}