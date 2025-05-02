package manuelklyukvin.contacts_app.main.data_sources

import android.content.Context
import manuelklyukvin.contacts_app.main.models.DataContact

class AndroidContactDataSource(private val context: Context) : ContactDataSource {
    override suspend fun getContacts(): List<DataContact> {
        return emptyList()
    }
}