package manuelklyukvin.contacts_app.main.data_sources

import manuelklyukvin.contacts_app.main.models.DataRawContact

interface RawContactDataSource {
    suspend fun getRawContacts(): List<DataRawContact>
}