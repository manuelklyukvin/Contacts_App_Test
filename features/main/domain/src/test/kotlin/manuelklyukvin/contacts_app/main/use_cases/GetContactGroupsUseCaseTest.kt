package manuelklyukvin.contacts_app.main.use_cases

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import manuelklyukvin.contacts_app.core.utils.logger.models.Logger
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.models.DomainContact
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetContactGroupsUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getContactsUseCase: GetContactsUseCase
    private lateinit var logger: Logger
    private lateinit var getContactGroupsUseCase: GetContactGroupsUseCase

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getContactsUseCase = mockk()
        logger = mockk(relaxed = true)
        getContactGroupsUseCase = GetContactGroupsUseCase(getContactsUseCase, logger)
    }

    @Test
    fun `should group contacts by first letter and sort groups`() = runTest {
        val contacts = listOf(
            DomainContact(null, "Bob", "111"),
            DomainContact(null, "Alice", "222"),
            DomainContact(null, "billy", "333")
        )
        coEvery { getContactsUseCase() } returns contacts

        val getContactGroupsResult = getContactGroupsUseCase()
        
        assertTrue(getContactGroupsResult is OperationResult.Success)
        val contactGroups = getContactGroupsResult.data

        assertEquals(2, contactGroups.size)

        assertEquals('A', contactGroups[0].header)
        assertEquals(listOf("Alice"), contactGroups[0].contacts.map { it.name })

        assertEquals('B', contactGroups[1].header)
        assertEquals(listOf("billy", "Bob"), contactGroups[1].contacts.map { it.name })
    }

    @Test
    fun `should use # for non-letter first characters`() = runTest {
        val contacts = listOf(
            DomainContact(null, "123Test", "111"),
            DomainContact(null, "Bob", "222")
        )
        coEvery { getContactsUseCase() } returns contacts

        val getContactGroupsResult = getContactGroupsUseCase()
        
        assertTrue(getContactGroupsResult is OperationResult.Success)
        val contactGroups = getContactGroupsResult.data

        assertEquals(2, contactGroups.size)

        assertEquals('B', contactGroups[0].header)
        assertEquals(listOf("Bob"), contactGroups[0].contacts.map { it.name })

        assertEquals('#', contactGroups[1].header)
        assertEquals(listOf("123Test"), contactGroups[1].contacts.map { it.name })
    }

    @Test
    fun `should return empty list when no contacts`() = runTest {
        coEvery { getContactsUseCase() } returns emptyList()

        val getContactGroupsResult = getContactGroupsUseCase()
        
        assertTrue(getContactGroupsResult is OperationResult.Success)
        assertTrue(getContactGroupsResult.data.isEmpty())
    }

    @Test
    fun `should handle empty contact names`() = runTest {
        val contacts = listOf(
            DomainContact(null, "", "111"),
            DomainContact(null, "   ", "222"),
            DomainContact(null, "Alice", "333")
        )
        coEvery { getContactsUseCase() } returns contacts

        val getContactGroupsResult = getContactGroupsUseCase()
        
        assertTrue(getContactGroupsResult is OperationResult.Success)
        val contactGroups = getContactGroupsResult.data

        assertEquals(2, contactGroups.size)

        assertEquals('A', contactGroups[0].header)
        assertEquals(listOf("Alice"), contactGroups[0].contacts.map { it.name })

        assertEquals('#', contactGroups[1].header)
        assertEquals(2, contactGroups[1].contacts.size)
    }

    @Test
    fun `should throw CancellationException`() = runTest {
        val errorMessage = "Cancel"
        coEvery { getContactsUseCase() } throws CancellationException(errorMessage)
        val getContactGroupsResult = assertThrows<CancellationException> { getContactGroupsUseCase() }
        assertEquals(errorMessage, getContactGroupsResult.message)
    }

    @Test
    fun `should return error on exception`() = runTest {
        val errorMessage = "Error"
        coEvery { getContactsUseCase() } throws Exception(errorMessage)

        val getContactGroupsResult = getContactGroupsUseCase()
        
        assertTrue(getContactGroupsResult is OperationResult.Error)
        assertEquals(errorMessage, getContactGroupsResult.error)
    }

    @Test
    fun `should sort special characters to the end`() = runTest {
        val contacts = listOf(
            DomainContact(null, "Alice", "111"),
            DomainContact(null, "1Test", "222"),
            DomainContact(null, "Bob", "333"),
            DomainContact(null, "#Test", "444")
        )
        coEvery { getContactsUseCase() } returns contacts

        val getContactGroupsResult = getContactGroupsUseCase()
        
        assertTrue(getContactGroupsResult is OperationResult.Success)
        val contactGroups = getContactGroupsResult.data

        assertEquals(3, contactGroups.size)

        assertEquals('A', contactGroups[0].header)
        assertEquals('B', contactGroups[1].header)

        assertEquals('#', contactGroups[2].header)
        assertEquals(listOf("#Test", "1Test"), contactGroups[2].contacts.map { it.name })
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}