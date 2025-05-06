package manuelklyukvin.contacts_app.main.contacts.use_cases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import manuelklyukvin.contacts_app.core.utils.logger.models.Logger
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.contacts.models.DomainContactGroup
import kotlin.coroutines.cancellation.CancellationException

class GetContactGroupsUseCase(
    private val getContactsUseCase: GetContactsUseCase,
    private val groupContactsUseCase: GroupContactsUseCase,
    private val logger: Logger
) {
    suspend operator fun invoke() = try {
        withContext(Dispatchers.IO) {
            val contacts = getContactsUseCase()
            val groupedContacts = groupContactsUseCase(contacts)
            val contactGroups = groupedContacts.map { (header, items) ->
                DomainContactGroup(header, items)
            }
            OperationResult.Success(contactGroups)
        }
    } catch (e: CancellationException) {
        logger.error("GetContactGroupsUseCase: Cancellation", e)
        throw e
    } catch (e: Exception) {
        logger.error("GetContactGroupsUseCase: Error fetching contacts", e)
        OperationResult.Error(e.message)
    }
}