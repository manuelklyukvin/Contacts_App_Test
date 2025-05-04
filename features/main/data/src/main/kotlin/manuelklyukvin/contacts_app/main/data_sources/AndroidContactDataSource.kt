package manuelklyukvin.contacts_app.main.data_sources

import android.content.Context
import android.provider.ContactsContract
import androidx.core.database.getStringOrNull
import manuelklyukvin.contacts_app.main.models.DataRawContact

class AndroidContactDataSource(private val context: Context) : ContactDataSource {
    override suspend fun getRawContacts(): List<DataRawContact> {
        val rawContactsMap = mutableMapOf<String, DataRawContact>()

        context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.PHOTO_URI,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            ),
            "${ContactsContract.Contacts.HAS_PHONE_NUMBER} = 1",
            null,
            null
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
            val photoUriIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI)
            val nameIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

            while (cursor.moveToNext()) {
                val id = cursor.getString(idIndex)
                val photoUri = cursor.getStringOrNull(photoUriIndex)
                val name = cursor.getString(nameIndex)
                val rawPhoneNumber = getPrimaryRawPhoneNumber(id)

                if (!rawPhoneNumber.isNullOrEmpty()) {
                    rawContactsMap[id] = DataRawContact(
                        photoUri = photoUri,
                        name = name,
                        rawPhoneNumber = rawPhoneNumber
                    )
                }
            }
        }

        return rawContactsMap.values.toList()
    }

    private fun getPrimaryRawPhoneNumber(id: String): String? {
        var primaryRawPhoneNumber: String? = null

        context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.IS_PRIMARY
            ),
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
            arrayOf(id),
            null
        )?.use { cursor ->
            val rawPhoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val isPrimaryIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.IS_PRIMARY)

            while (cursor.moveToNext()) {
                val isPrimary = cursor.getInt(isPrimaryIndex)
                if (isPrimary == 1) {
                    primaryRawPhoneNumber = cursor.getString(rawPhoneNumberIndex)
                    break
                }
            }

            if (primaryRawPhoneNumber == null && cursor.moveToFirst()) {
                primaryRawPhoneNumber = cursor.getString(rawPhoneNumberIndex)
            }
        }

        return primaryRawPhoneNumber
    }
}