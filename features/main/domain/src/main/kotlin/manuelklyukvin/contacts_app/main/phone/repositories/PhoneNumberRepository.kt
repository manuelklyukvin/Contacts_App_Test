package manuelklyukvin.contacts_app.main.phone.repositories

interface PhoneNumberRepository {
    suspend fun getPhoneNumber(contactId: Int): String?
}