package manuelklyukvin.contacts_app.main.models

import manuelklyukvin.contacts_app.main.contacts.models.DomainContact

data class PresentationContact(
    val photoUri: String?,
    val name: String,
    val phoneNumber: String
)

fun DomainContact.toPresentation() = PresentationContact(
    photoUri = photoUri,
    name = name,
    phoneNumber = phoneNumber
)