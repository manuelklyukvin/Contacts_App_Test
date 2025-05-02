package manuelklyukvin.contacts_app.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import manuelklyukvin.contacts_app.core.ui.components.texts.AppText
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String? = null,
    isEnabled: Boolean = true,
    containerColor: Color = AppTheme.colorScheme.primary,
    contentColor: Color = AppTheme.colorScheme.onPrimary,
    content: @Composable (() -> Unit)? = null
) {
    val alpha = 0.5f
    val disabledContainerColor = containerColor.copy(alpha = alpha)
    val disabledContentColor = contentColor.copy(alpha = alpha)

    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        shape = AppTheme.shapes.roundedCornerShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        contentPadding = PaddingValues(horizontal = AppTheme.shapes.paddingExtraLarge)
    ) {
        if (text != null) {
            AppText(
                text = text,
                color = if (isEnabled) contentColor else disabledContentColor
            )
        } else if (content != null) content()
    }
}

@Preview
@Composable
private fun LightPrimaryAppButtonPreview() {
    PrimaryAppButtonPreview()
}

@Preview
@Composable
private fun LightSecondaryAppButtonPreview() {
    SecondaryAppButtonPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPrimaryAppButtonPreview() {
    PrimaryAppButtonPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkSecondaryAppButtonPreview() {
    SecondaryAppButtonPreview()
}

@Composable
private fun PrimaryAppButtonPreview() {
    AppTheme {
        Column {
            AppButton(
                text = "Enabled",
                onClick = { }
            )
            AppButton(
                text = "Disabled",
                onClick = { },
                isEnabled = false
            )
        }
    }
}

@Composable
private fun SecondaryAppButtonPreview() {
    AppTheme {
        Column {
            AppButton(
                text = "Enabled",
                onClick = { },
                containerColor = AppTheme.colorScheme.secondary,
                contentColor = AppTheme.colorScheme.onSecondary
            )
            AppButton(
                text = "Disabled",
                onClick = { },
                isEnabled = false,
                containerColor = AppTheme.colorScheme.secondary,
                contentColor = AppTheme.colorScheme.onSecondary
            )
        }
    }
}