package manuelklyukvin.contacts_app.main.data_sources

import manuelklyukvin.contacts_app.main.models.DataRawContact

interface ContactDataSource {
    suspend fun getRawContacts(): List<DataRawContact>
}