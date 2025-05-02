package manuelklyukvin.contacts_app.main.data_sources

import manuelklyukvin.contacts_app.main.models.DataContact

interface ContactDataSource {
    suspend fun getContacts(): List<DataContact>
}