package manuelklyukvin.contacts_app.main.contacts.data_sources

import android.content.Context
import android.provider.ContactsContract
import androidx.core.database.getStringOrNull
import manuelklyukvin.contacts_app.main.contacts.models.DataRawContact

class RawContactDataSourceImpl(private val context: Context) : RawContactDataSource {
    override suspend fun getRawContacts(): List<DataRawContact> {
        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.PHOTO_URI,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        )
        val selection = "${ContactsContract.Contacts.HAS_PHONE_NUMBER} = 1"

        return context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            selection,
            null,
            null
        )?.use { cursor ->
            val rawContacts = mutableListOf<DataRawContact>()

            val idIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
            val photoUriIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI)
            val nameIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(idIndex)
                val photoUri = cursor.getStringOrNull(photoUriIndex)
                val name = cursor.getStringOrNull(nameIndex)

                if (!name.isNullOrBlank()) {
                    rawContacts.add(
                        DataRawContact(
                            id = id,
                            photoUri = photoUri,
                            name = name
                        )
                    )
                }
            }
            rawContacts.distinctBy { it.id }
        } ?: emptyList()
    }
}