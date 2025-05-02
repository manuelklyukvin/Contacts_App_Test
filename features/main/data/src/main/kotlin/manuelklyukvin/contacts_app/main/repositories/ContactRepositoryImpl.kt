package manuelklyukvin.contacts_app.main.repositories

import manuelklyukvin.contacts_app.main.data_sources.ContactDataSource
import manuelklyukvin.contacts_app.main.models.toDomain

class ContactRepositoryImpl(private val contactDataSource: ContactDataSource) : ContactRepository {
    override suspend fun getContacts() = contactDataSource.getContacts().map { it.toDomain() }
}