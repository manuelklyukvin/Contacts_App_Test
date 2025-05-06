package manuelklyukvin.contacts_app.main.contacts.repositories

import manuelklyukvin.contacts_app.main.contacts.data_sources.RawContactDataSource
import manuelklyukvin.contacts_app.main.contacts.models.toDomain

class RawContactRepositoryImpl(private val rawContactDataSource: RawContactDataSource) : RawContactRepository {
    override suspend fun getRawContacts() = rawContactDataSource.getRawContacts().map { it.toDomain() }
}