package manuelklyukvin.contacts_app.main.repositories

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import manuelklyukvin.contacts_app.main.data_sources.ContactDataSource
import manuelklyukvin.contacts_app.main.models.DataRawContact
import manuelklyukvin.contacts_app.main.models.DomainRawContact
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ContactRepositoryImplTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var contactDataSource: ContactDataSource
    private lateinit var contactRepository: ContactRepository

    @BeforeEach
    fun setup() {
        contactDataSource = mockk()
        contactRepository = ContactRepositoryImpl(contactDataSource)
    }

    @Test
    fun `returns list of mapped domain contacts`() = runTest(testDispatcher) {
        val rawContacts = listOf(
            DataRawContact(null, "Alice", "123456"),
            DataRawContact(null, "Bob", "987654")
        )
        coEvery { contactDataSource.getRawContacts() } returns rawContacts

        val expected = listOf(
            DomainRawContact(null, "Alice", "123456"),
            DomainRawContact(null, "Bob", "987654")
        )

        val getRawContactsResult = contactRepository.getRawContacts()
        assertEquals(expected, getRawContactsResult)
    }

    @Test
    fun `returns empty list when datasource is empty`() = runTest(testDispatcher) {
        coEvery { contactDataSource.getRawContacts() } returns emptyList()
        val getRawContactsResult = contactRepository.getRawContacts()
        assertEquals(emptyList(), getRawContactsResult)
    }

    @Test
    fun `throws exception from datasource`() = runTest(testDispatcher) {
        coEvery { contactDataSource.getRawContacts() } throws Exception("Error")
        val exception = assertFailsWith<Exception> { contactRepository.getRawContacts() }
        assertEquals("Error", exception.message)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}