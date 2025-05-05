package manuelklyukvin.contacts_app.main.repositories

import manuelklyukvin.contacts_app.main.data_sources.RawContactDataSource
import manuelklyukvin.contacts_app.main.models.toDomain

class RawContactRepositoryImpl(private val rawContactDataSource: RawContactDataSource) : RawContactRepository {
    override suspend fun getRawContacts() = rawContactDataSource.getRawContacts().map { it.toDomain() }
}