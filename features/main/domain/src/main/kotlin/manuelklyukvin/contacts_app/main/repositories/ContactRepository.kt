package manuelklyukvin.contacts_app.main.repositories

import manuelklyukvin.contacts_app.main.models.DomainContact

interface ContactRepository {
    suspend fun getContacts(): List<DomainContact>
}