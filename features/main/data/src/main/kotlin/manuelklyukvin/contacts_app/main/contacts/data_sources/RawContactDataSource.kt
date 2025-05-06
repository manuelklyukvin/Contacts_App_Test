package manuelklyukvin.contacts_app.main.contacts.data_sources

import manuelklyukvin.contacts_app.main.contacts.models.DataRawContact

interface RawContactDataSource {
    suspend fun getRawContacts(): List<DataRawContact>
}