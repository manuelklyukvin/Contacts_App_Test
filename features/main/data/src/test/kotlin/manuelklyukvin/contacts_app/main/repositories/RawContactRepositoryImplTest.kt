package manuelklyukvin.contacts_app.main.repositories

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import manuelklyukvin.contacts_app.main.data_sources.RawContactDataSource
import manuelklyukvin.contacts_app.main.models.DataRawContact
import manuelklyukvin.contacts_app.main.models.DomainRawContact
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class RawContactRepositoryImplTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var rawContactDataSource: RawContactDataSource
    private lateinit var rawContactRepository: RawContactRepository

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        rawContactDataSource = mockk()
        rawContactRepository = RawContactRepositoryImpl(rawContactDataSource)
    }

    @Test
    fun `should return list of mapped domain contacts`() = runTest {
        val rawContacts = listOf(
            DataRawContact(null, "Alice", "123456"),
            DataRawContact(null, "Bob", "987654")
        )
        coEvery { rawContactDataSource.getRawContacts() } returns rawContacts
        val expected = listOf(
            DomainRawContact(null, "Alice", "123456"),
            DomainRawContact(null, "Bob", "987654")
        )

        val getRawContactsResult = rawContactRepository.getRawContacts()

        assertEquals(expected, getRawContactsResult)
    }

    @Test
    fun `should return empty list when data source is empty`() = runTest {
        coEvery { rawContactDataSource.getRawContacts() } returns emptyList()
        val getRawContactsResult = rawContactRepository.getRawContacts()
        assertEquals(emptyList(), getRawContactsResult)
    }

    @Test
    fun `should throw exception from data source`() = runTest {
        val errorMessage = "Error"
        coEvery { rawContactDataSource.getRawContacts() } throws Exception(errorMessage)
        val getRawContactsResult = assertThrows<Exception> { rawContactRepository.getRawContacts() }
        assertEquals(errorMessage, getRawContactsResult.message)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}