package manuelklyukvin.contacts_app.main.models

data class PresentationContactGroup(
    val header: Char,
    val contacts: List<PresentationContact>
)

fun DomainContactGroup.toPresentation() = PresentationContactGroup(
    header = header,
    contacts = contacts.map { it.toPresentation() }
)