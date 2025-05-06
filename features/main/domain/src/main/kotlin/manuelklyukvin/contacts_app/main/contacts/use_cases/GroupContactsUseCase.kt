package manuelklyukvin.contacts_app.main.contacts.use_cases

import manuelklyukvin.contacts_app.main.contacts.models.DomainContact

class GroupContactsUseCase {
    operator fun invoke(contacts: List<DomainContact>) = contacts
        .filter { it.name.isNotBlank() }
        .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
        .groupBy { contact ->
            contact.name
                .firstOrNull()
                ?.uppercaseChar()
                ?.takeIf { it.isLetter() } ?: '#'
        }
        .toSortedMap(compareBy { if (it == '#') Char.MAX_VALUE else it })
}