package manuelklyukvin.contacts_app.main.models

data class DataContact(
    val firstname: String,
    val lastname: String,
    val phoneNumber: String
)

fun DataContact.toDomain() = DomainContact(
    firstname = firstname,
    lastname = lastname,
    phoneNumber = phoneNumber
)