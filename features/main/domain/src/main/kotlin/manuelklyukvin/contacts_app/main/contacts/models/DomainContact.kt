package manuelklyukvin.contacts_app.main.contacts.models

data class DomainContact(
    val id: Int,
    val photoUri: String?,
    val name: String,
    val phoneNumber: String
)