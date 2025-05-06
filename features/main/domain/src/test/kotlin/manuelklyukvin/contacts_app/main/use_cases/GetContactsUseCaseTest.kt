package manuelklyukvin.contacts_app.main.use_cases

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.contacts.models.DomainContact
import manuelklyukvin.contacts_app.main.contacts.models.DomainRawContact
import manuelklyukvin.contacts_app.main.contacts.repositories.RawContactRepository
import manuelklyukvin.contacts_app.main.contacts.use_cases.GetContactsUseCase
import manuelklyukvin.contacts_app.main.phone.use_cases.FormatPhoneNumberUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetContactsUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var rawContactRepository: RawContactRepository
    private lateinit var formatPhoneNumberUseCase: FormatPhoneNumberUseCase
    private lateinit var getContactsUseCase: GetContactsUseCase

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        rawContactRepository = mockk()
        formatPhoneNumberUseCase = mockk()
        getContactsUseCase = GetContactsUseCase(rawContactRepository, formatPhoneNumberUseCase)
    }

    @Test
    fun `should return sorted and formatted contacts on success`() = runTest {
        val rawContacts = listOf(
            DomainRawContact(null, "Alice", "89161234567"),
            DomainRawContact(null, "Bob", "+7 (916) 000-00-00")
        )
        coEvery { rawContactRepository.getRawContacts() } returns rawContacts
        every { formatPhoneNumberUseCase("89161234567") } returns OperationResult.Success("+7 (916) 123-45-67")
        every { formatPhoneNumberUseCase("+7 (916) 000-00-00") } returns OperationResult.Success("+7 (916) 000-00-00")

        val expected = listOf(
            DomainContact(null, "Alice", "+7 (916) 123-45-67"),
            DomainContact(null, "Bob", "+7 (916) 000-00-00")
        )

        val getContactsResult = getContactsUseCase()
        assertEquals(expected, getContactsResult)
    }

    @Test
    fun `should skip contacts with invalid phone numbers`() = runTest {
        val rawContacts = listOf(
            DomainRawContact(null, "Alice", "bad-number"),
            DomainRawContact(null, "Bob", "89161234567")
        )
        coEvery { rawContactRepository.getRawContacts() } returns rawContacts
        every { formatPhoneNumberUseCase("bad-number") } returns OperationResult.Error("Error")
        every { formatPhoneNumberUseCase("89161234567") } returns OperationResult.Success("+7 (916) 123-45-67")

        val expected = listOf(DomainContact(null, "Bob", "+7 (916) 123-45-67"))

        val getContactsResult = getContactsUseCase()
        assertEquals(expected, getContactsResult)
    }

    @Test
    fun `should return empty list when all contacts are invalid`() = runTest {
        val rawContacts = listOf(
            DomainRawContact(null, "Alice", "invalid1"),
            DomainRawContact(null, "Bob", "invalid2")
        )
        coEvery { rawContactRepository.getRawContacts() } returns rawContacts
        every { formatPhoneNumberUseCase(any()) } returns OperationResult.Error("Error")

        val getContactsResult = getContactsUseCase()
        assertEquals(emptyList(), getContactsResult)
    }

    @Test
    fun `should return OperationResultError on repository exception`() = runTest {
        val errorMessage = "Error"
        coEvery { rawContactRepository.getRawContacts() } throws Exception(errorMessage)
        val getContactsResult = assertThrows<Exception> { getContactsUseCase() }
        assertEquals(errorMessage, getContactsResult.message)
    }

    @Test
    fun `should throw CancellationException`() = runTest {
        val errorMessage = "Error"
        coEvery { rawContactRepository.getRawContacts() } throws CancellationException(errorMessage)
        val getContactsResult = assertThrows<CancellationException> { getContactsUseCase() }
        assertEquals(errorMessage, getContactsResult.message)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}