package manuelklyukvin.contacts_app.core.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import manuelklyukvin.contacts_app.core.R
import manuelklyukvin.contacts_app.core.ui.components.AppButton
import manuelklyukvin.contacts_app.core.ui.components.texts.AppText
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme

@Composable
fun ErrorScreen(error: String?, onRetryButtonClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.shapes.paddingExtraLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = error ?: stringResource(R.string.default_error),
            style = AppTheme.typography.title,
            color = AppTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(AppTheme.shapes.paddingExtraSmall))
        AppButton(
            text = stringResource(R.string.retry_button),
            onClick = onRetryButtonClicked
        )
    }
}

@Preview
@Composable
private fun LightErrorScreenPreview() {
    ErrorScreenPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkErrorScreenPreview() {
    ErrorScreenPreview()
}

@Composable
private fun ErrorScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.background
        ) {
            ErrorScreen(
                error = null,
                onRetryButtonClicked = { }
            )
        }
    }
}