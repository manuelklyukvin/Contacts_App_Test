package manuelklyukvin.contacts_app.main.use_cases

import manuelklyukvin.contacts_app.main.models.DomainContact

class SortContactsUseCase {
    operator fun invoke(contacts: List<DomainContact>) = contacts.sortedWith(
        compareBy(String.CASE_INSENSITIVE_ORDER) { it.name }
    )
}