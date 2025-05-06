package manuelklyukvin.contacts_app.main.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import manuelklyukvin.contacts_app.core.ui.components.AppCard
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme
import manuelklyukvin.contacts_app.core.ui.utils.shimmerEffect

@Composable
internal fun LoadingMainScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.shapes.screenPadding)
            .clip(AppTheme.shapes.roundedCornerShape),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.screenPadding),
        userScrollEnabled = false
    ) {
        items(25) {
            LoadingContactCard()
        }
    }
}

@Composable
private fun LoadingContactCard() {
    AppCard(
        modifier = Modifier.fillMaxWidth(),
        areDefaultPaddingsEnabled = true
    ) {
        Box(
            modifier = Modifier
                .size(AppTheme.shapes.sizeLarge)
                .clip(RoundedCornerShape(100))
                .shimmerEffect()
        )
    }
}

@Preview
@Composable
private fun LightLoadingMainScreenPreview() {
    LoadingMainScreenPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkLoadingMainScreenPreview() {
    LoadingMainScreenPreview()
}

@Composable
private fun LoadingMainScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.background
        ) {
            LoadingMainScreen()
        }
    }
}