package manuelklyukvin.contacts_app.main.contacts.models

data class DataRawContact(
    val id: Int,
    val photoUri: String?,
    val name: String
)

fun DataRawContact.toDomain() = DomainRawContact(
    id = id,
    photoUri = photoUri,
    name = name
)