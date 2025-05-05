package manuelklyukvin.contacts_app.main.data_sources

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import androidx.core.database.getStringOrNull
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class AndroidRawContactDataSourceTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var context: Context
    private lateinit var contentResolver: ContentResolver
    private lateinit var contactCursor: Cursor
    private lateinit var phoneNumberCursor: Cursor
    private lateinit var dataSource: RawContactDataSource

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        context = mockk()
        contentResolver = mockk()
        contactCursor = mockk(relaxed = true)
        phoneNumberCursor = mockk(relaxed = true)
        every { context.contentResolver } returns contentResolver
        dataSource = AndroidRawContactDataSource(context)
    }

    @Test
    fun `should return contact with primary phone number`() = runTest {
        val contactId = "1"
        val name = "John Doe"
        val photoUri = "photo.jpg"
        val phoneNumber = "+1234567890"

        every {
            contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                any(),
                any(),
                null,
                null
            )
        } returns contactCursor

        every { contactCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID) } returns 0
        every { contactCursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI) } returns 1
        every { contactCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY) } returns 2
        every { contactCursor.moveToNext() } returns true andThen false
        every { contactCursor.getString(0) } returns contactId
        every { contactCursor.getStringOrNull(1) } returns photoUri
        every { contactCursor.getString(2) } returns name

        every {
            contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                any(),
                "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                arrayOf(contactId),
                null
            )
        } returns phoneNumberCursor

        every { phoneNumberCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER) } returns 0
        every { phoneNumberCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.IS_PRIMARY) } returns 1
        every { phoneNumberCursor.moveToNext() } returns true andThen false
        every { phoneNumberCursor.getInt(1) } returns 1
        every { phoneNumberCursor.getString(0) } returns phoneNumber

        val getRawContactsResult = dataSource.getRawContacts()

        assertEquals(1, getRawContactsResult.size)
        val contact = getRawContactsResult[0]
        assertEquals(contactId, "1")
        assertEquals(name, contact.name)
        assertEquals(phoneNumber, contact.rawPhoneNumber)
        assertEquals(photoUri, contact.photoUri)
    }

    @Test
    fun `should return empty list when no contacts`() = runTest {
        every {
            contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                any(),
                any(),
                null,
                null
            )
        } returns null

        val getRawContactsResult = dataSource.getRawContacts()
        assertTrue(getRawContactsResult.isEmpty())
    }

    @Test
    fun `should skip contacts without phone numbers`() = runTest {
        val contactId = "2"
        val name = "Jane Doe"
        val photoUri = null

        every {
            contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                any(),
                any(),
                null,
                null
            )
        } returns contactCursor

        every { contactCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID) } returns 0
        every { contactCursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI) } returns 1
        every { contactCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY) } returns 2
        every { contactCursor.moveToNext() } returns true andThen false
        every { contactCursor.getString(0) } returns contactId
        every { contactCursor.getStringOrNull(1) } returns photoUri
        every { contactCursor.getString(2) } returns name

        every {
            contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                any(),
                any(),
                arrayOf(contactId),
                null
            )
        } returns phoneNumberCursor

        every { phoneNumberCursor.moveToNext() } returns false
        every { phoneNumberCursor.moveToFirst() } returns false

        val getRawContactsResult = dataSource.getRawContacts()
        assertTrue(getRawContactsResult.isEmpty())
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}