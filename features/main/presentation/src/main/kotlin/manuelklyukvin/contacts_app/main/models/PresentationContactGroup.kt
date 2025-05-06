package manuelklyukvin.contacts_app.main.models

import manuelklyukvin.contacts_app.main.contacts.models.DomainContactGroup

data class PresentationContactGroup(
    val header: Char,
    val contacts: List<PresentationContact>
)

fun DomainContactGroup.toPresentation() = PresentationContactGroup(
    header = header,
    contacts = contacts.map { it.toPresentation() }
)