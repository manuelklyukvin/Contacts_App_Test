package manuelklyukvin.contacts_app.main.models

data class PresentationContact(
    val firstname: String,
    val lastname: String,
    val phoneNumber: String
)

fun DomainContact.toPresentation() = PresentationContact(
    firstname = firstname,
    lastname = lastname,
    phoneNumber = phoneNumber
)