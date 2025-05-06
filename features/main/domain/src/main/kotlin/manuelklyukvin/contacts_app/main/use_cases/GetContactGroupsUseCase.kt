package manuelklyukvin.contacts_app.main.use_cases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import manuelklyukvin.contacts_app.core.utils.logger.models.Logger
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.models.DomainContact
import manuelklyukvin.contacts_app.main.models.DomainContactGroup
import kotlin.coroutines.cancellation.CancellationException

class GetContactGroupsUseCase(
    private val getContactsUseCase: GetContactsUseCase,
    private val logger: Logger
) {
    suspend operator fun invoke() = try {
        withContext(Dispatchers.IO) {
            val contacts = getContactsUseCase()
            val groupedContacts = groupContacts(contacts)
            val contactGroups = groupedContacts.map { (header, items) ->
                DomainContactGroup(header, items)
            }
            OperationResult.Success(contactGroups)
        }
    } catch (e: CancellationException) {
        logger.error(
            message = "GetContactGroupsUseCase: Cancellation",
            throwable = e
        )
        throw e
    } catch (e: Exception) {
        logger.error(
            message = "GetContactGroupsUseCase: Error fetching contacts",
            throwable = e
        )
        OperationResult.Error(e.message)
    }

    private fun groupContacts(contacts: List<DomainContact>) = contacts
        .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
        .groupBy { contact ->
            contact.name
                .firstOrNull()
                ?.uppercaseChar()
                ?.takeIf { it.isLetter() } ?: '#'
        }
        .toSortedMap(compareBy { if (it == '#') Char.MAX_VALUE else it })
}