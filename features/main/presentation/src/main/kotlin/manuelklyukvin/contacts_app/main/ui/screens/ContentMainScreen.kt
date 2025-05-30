package manuelklyukvin.contacts_app.main.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import manuelklyukvin.contacts_app.core.ui.components.AppCard
import manuelklyukvin.contacts_app.core.ui.components.images.AppIcon
import manuelklyukvin.contacts_app.core.ui.components.images.AppImage
import manuelklyukvin.contacts_app.core.ui.components.texts.AppLineText
import manuelklyukvin.contacts_app.core.ui.theme.AppTheme
import manuelklyukvin.contacts_app.core.ui.utils.noIndicationClickable
import manuelklyukvin.contacts_app.main.R
import manuelklyukvin.contacts_app.main.models.PresentationContact
import manuelklyukvin.contacts_app.main.models.PresentationContactGroup
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainIntent
import manuelklyukvin.contacts_app.main.ui.view_models.models.MainState

@Composable
internal fun ContentMainScreen(state: MainState, onIntent: (MainIntent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.shapes.screenPadding)
            .clip(AppTheme.shapes.roundedCornerShape),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.screenPadding)
    ) {
        items(state.contactGroups) { contactGroup ->
            ContactGroup(contactGroup, onIntent)
        }
    }
}

@Composable
private fun ContactGroup(contactGroup: PresentationContactGroup, onIntent: (MainIntent) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingSmall)) {
        AppLineText(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = AppTheme.colorScheme.secondary,
                    shape = AppTheme.shapes.roundedCornerShape
                )
                .padding(
                    vertical = AppTheme.shapes.paddingExtraSmall,
                    horizontal = AppTheme.shapes.paddingSmall
                ),
            text = contactGroup.header.toString(),
            style = AppTheme.typography.title,
            color = AppTheme.colorScheme.onSecondary
        )
        contactGroup.contacts.forEach { contact ->
            ContactCard(contact, onIntent)
        }
    }
}

@Composable
private fun ContactCard(contact: PresentationContact, onIntent: (MainIntent) -> Unit) {
    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .noIndicationClickable { onIntent(MainIntent.OnContactClicked(contact.phoneNumber)) },
        areDefaultPaddingsEnabled = true
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (contact.photoUri != null) {
                AppImage(
                    modifier = Modifier
                        .size(AppTheme.shapes.sizeLarge)
                        .clip(RoundedCornerShape(100)),
                    model = contact.photoUri
                )
            } else {
                AppIcon(
                    modifier = Modifier
                        .size(AppTheme.shapes.sizeLarge)
                        .clip(RoundedCornerShape(100)),
                    model = painterResource(R.drawable.contact)
                )
            }
            Spacer(Modifier.width(AppTheme.shapes.paddingSmall))
            Column {
                AppLineText(
                    text = contact.name,
                    style = AppTheme.typography.title,
                    color = AppTheme.colorScheme.primary
                )
                AppLineText(text = contact.phoneNumber)
            }
        }
    }
}

@Preview
@Composable
private fun LightContentMainScreenPreview() {
    ContentMainScreenPreview()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkContentMainScreenPreview() {
    ContentMainScreenPreview()
}

@Composable
private fun ContentMainScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.background
        ) {
            ContentMainScreen(
                state = MainState(
                    contactGroups = List(2) {
                        PresentationContactGroup(
                            header = 'N',
                            contacts = List(3) {
                                PresentationContact(
                                    id = 0,
                                    photoUri = null,
                                    name = "Name",
                                    phoneNumber = "+7 (900) 000-00-00"
                                )
                            }
                        )
                    }
                ),
                onIntent = { }
            )
        }
    }
}