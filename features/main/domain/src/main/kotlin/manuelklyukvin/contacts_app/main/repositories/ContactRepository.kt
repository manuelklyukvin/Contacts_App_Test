package manuelklyukvin.contacts_app.main.repositories

import manuelklyukvin.contacts_app.main.models.DomainRawContact

interface ContactRepository {
    suspend fun getRawContacts(): List<DomainRawContact>
}