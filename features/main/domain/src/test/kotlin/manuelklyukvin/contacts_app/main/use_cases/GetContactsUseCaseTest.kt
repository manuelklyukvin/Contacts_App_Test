package manuelklyukvin.contacts_app.main.use_cases

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.models.DomainContact
import manuelklyukvin.contacts_app.main.models.DomainRawContact
import manuelklyukvin.contacts_app.main.repositories.ContactRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetContactsUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var contactRepository: ContactRepository
    private lateinit var formatPhoneNumberUseCase: FormatPhoneNumberUseCase
    private lateinit var sortContactsUseCase: SortContactsUseCase
    private lateinit var getContactsUseCase: GetContactsUseCase

    @BeforeEach
    fun setup() {
        contactRepository = mockk()
        formatPhoneNumberUseCase = mockk()
        sortContactsUseCase = mockk()
        getContactsUseCase = GetContactsUseCase(contactRepository, formatPhoneNumberUseCase, sortContactsUseCase)
    }

    @Test
    fun `returns sorted and formatted contacts on success`() = runTest(testDispatcher) {
        val rawContacts = listOf(
            DomainRawContact(null, "Alice", "89161234567"),
            DomainRawContact(null, "Bob", "+7 (916) 000-00-00")
        )
        coEvery { contactRepository.getRawContacts() } returns rawContacts

        every { formatPhoneNumberUseCase("89161234567") } returns OperationResult.Success("+7 (916) 123-45-67")
        every { formatPhoneNumberUseCase("+7 (916) 000-00-00") } returns OperationResult.Success("+7 (916) 000-00-00")

        val expected = listOf(
            DomainContact(null, "Alice", "+7 (916) 123-45-67"),
            DomainContact(null, "Bob", "+7 (916) 000-00-00")
        )
        every { sortContactsUseCase(any()) } returns expected

        val getContactsResult = getContactsUseCase()
        assertTrue(getContactsResult is OperationResult.Success)
        assertEquals(expected, getContactsResult.data)
    }

    @Test
    fun `skips contacts with invalid phone numbers`() = runTest(testDispatcher) {
        val rawContacts = listOf(
            DomainRawContact(null, "Alice", "bad-number"),
            DomainRawContact(null, "Bob", "89161234567")
        )
        coEvery { contactRepository.getRawContacts() } returns rawContacts

        every { formatPhoneNumberUseCase("bad-number") } returns OperationResult.Error("Error")
        every { formatPhoneNumberUseCase("89161234567") } returns OperationResult.Success("+7 (916) 123-45-67")

        val expected = listOf(DomainContact(null, "Bob", "+7 (916) 123-45-67"))
        every { sortContactsUseCase(any()) } returns expected

        val getContactsResult = getContactsUseCase()
        assertTrue(getContactsResult is OperationResult.Success)
        assertEquals(expected, getContactsResult.data)
    }

    @Test
    fun `returns empty list when all contacts are invalid`() = runTest(testDispatcher) {
        val rawContacts = listOf(
            DomainRawContact(null, "Alice", "invalid1"),
            DomainRawContact(null, "Bob", "invalid2")
        )
        coEvery { contactRepository.getRawContacts() } returns rawContacts

        every { formatPhoneNumberUseCase(any()) } returns OperationResult.Error("Error")

        every { sortContactsUseCase(emptyList()) } returns emptyList()

        val getContactsResult = getContactsUseCase()
        assertTrue(getContactsResult is OperationResult.Success)
        assertEquals(emptyList(), getContactsResult.data)
    }

    @Test
    fun `returns OperationResultError on repository exception`() = runTest(testDispatcher) {
        coEvery { contactRepository.getRawContacts() } throws Exception("Error")

        val getContactsResult = getContactsUseCase()
        assertTrue(getContactsResult is OperationResult.Error)
        assertEquals("Error", getContactsResult.error)
    }

    @Test
    fun `cancels when CancellationException is thrown`() = runTest(testDispatcher) {
        coEvery { contactRepository.getRawContacts() } throws CancellationException()
        assertThrows<CancellationException> { getContactsUseCase() }
    }
}