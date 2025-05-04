package manuelklyukvin.contacts_app.main.use_cases

import manuelklyukvin.contacts_app.main.models.DomainContact
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SortContactsUseCaseTest {
    private val sortContactsUseCase = SortContactsUseCase()

    @Test
    fun `should return contacts sorted alphabetically ignoring case`() {
        val contacts = listOf(
            DomainContact(null, "bob", "123"),
            DomainContact(null, "Alice", "456"),
            DomainContact(null, "charlie", "789")
        )
        val sortedContacts = sortContactsUseCase(contacts)
        assertEquals(listOf("Alice", "bob", "charlie"), sortedContacts.map { it.name })
    }

    @Test
    fun `should return empty list when input is empty`() {
        val contacts = emptyList<DomainContact>()
        val sortedContacts = sortContactsUseCase(contacts)
        assertEquals(emptyList<DomainContact>(), sortedContacts)
    }

    @Test
    fun `should return contacts sorted alphabetically ignoring case and languages`() {
        val contacts = listOf(
            DomainContact(null, "bob", "123"),
            DomainContact(null, "Alice", "456"),
            DomainContact(null, "алиса", "789"),
            DomainContact(null, "Паша", "789")
        )
        val sortedContacts = sortContactsUseCase(contacts)
        assertEquals(listOf("Alice", "bob", "алиса", "Паша"), sortedContacts.map { it.name })
    }

    @Test
    fun `should maintain order for contacts with equal names ignoring case`() {
        val contacts = listOf(
            DomainContact(null, "alice", "123"),
            DomainContact(null, "Alice", "456"),
            DomainContact(null, "ALICE", "789")
        )
        val sortedContacts = sortContactsUseCase(contacts)
        assertEquals(listOf("alice", "Alice", "ALICE"), sortedContacts.map { it.name })
    }

    @Test
    fun `should sort correctly with special characters`() {
        val contacts = listOf(
            DomainContact(null, "Émile", "111"),
            DomainContact(null, "Zara", "222"),
            DomainContact(null, "anna", "333")
        )
        val sortedContacts = sortContactsUseCase(contacts)
        assertEquals(listOf("anna", "Zara", "Émile"), sortedContacts.map { it.name })
    }

    @Test
    fun `should sort correctly with numbers`() {
        val contacts = listOf(
            DomainContact(null, "Катя", "111"),
            DomainContact(null, "алина", "222"),
            DomainContact(null, "1 мама", "333")
        )
        val sortedContacts = sortContactsUseCase(contacts)
        assertEquals(listOf("1 мама", "алина", "Катя"), sortedContacts.map { it.name })
    }
}