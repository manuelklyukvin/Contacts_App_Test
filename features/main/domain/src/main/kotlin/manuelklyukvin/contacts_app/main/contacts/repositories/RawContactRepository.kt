package manuelklyukvin.contacts_app.main.contacts.repositories

import manuelklyukvin.contacts_app.main.contacts.models.DomainRawContact

interface RawContactRepository {
    suspend fun getRawContacts(): List<DomainRawContact>
}