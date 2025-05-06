package manuelklyukvin.contacts_app.main.phone.data_sources

interface PhoneNumberDataSource {
    suspend fun getPhoneNumber(contactId: Int): String?
}