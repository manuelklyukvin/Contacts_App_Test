package manuelklyukvin.contacts_app.main.phone.repositoies

import manuelklyukvin.contacts_app.main.phone.data_sources.PhoneNumberDataSource
import manuelklyukvin.contacts_app.main.phone.repositories.PhoneNumberRepository

class PhoneNumberRepositoryImpl(private val phoneNumberDataSource: PhoneNumberDataSource) : PhoneNumberRepository {
    override suspend fun getPhoneNumber(contactId: Int) = phoneNumberDataSource.getPhoneNumber(contactId)
}