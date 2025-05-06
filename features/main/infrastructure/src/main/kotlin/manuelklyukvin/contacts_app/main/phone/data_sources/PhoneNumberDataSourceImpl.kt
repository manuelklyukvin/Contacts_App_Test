package manuelklyukvin.contacts_app.main.phone.data_sources

import android.content.Context
import android.provider.ContactsContract
import androidx.core.database.getStringOrNull

class PhoneNumberDataSourceImpl(private val context: Context) : PhoneNumberDataSource {
    override suspend fun getPhoneNumber(contactId: Int): String? {
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.IS_PRIMARY
        )
        val selection = "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = $contactId"
        val sortOrder = "${ContactsContract.CommonDataKinds.Phone.IS_PRIMARY} DESC LIMIT 1"

        return context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )?.use { cursor ->
            val phoneNumberIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val isPrimaryIndex = cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.IS_PRIMARY)

            when {
                cursor.moveToFirst() -> {
                    if (cursor.getInt(isPrimaryIndex) == 1) {
                        cursor.getStringOrNull(phoneNumberIndex)
                    } else {
                        cursor.getStringOrNull(phoneNumberIndex)
                    }
                }
                else -> null
            }
        }
    }
}