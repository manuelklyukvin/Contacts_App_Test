package manuelklyukvin.contacts_app.main.contacts.models

data class DataRawContact(
    val photoUri: String?,
    val name: String,
    val rawPhoneNumber: String
)

fun DataRawContact.toDomain() = DomainRawContact(
    photoUri = photoUri,
    name = name,
    rawPhoneNumber = rawPhoneNumber
)