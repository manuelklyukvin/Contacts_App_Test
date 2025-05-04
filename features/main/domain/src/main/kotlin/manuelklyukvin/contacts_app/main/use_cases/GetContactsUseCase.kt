package manuelklyukvin.contacts_app.main.use_cases

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.models.DomainContact
import manuelklyukvin.contacts_app.main.repositories.ContactRepository

class GetContactsUseCase(
    private val contactRepository: ContactRepository,
    private val formatPhoneNumberUseCase: FormatPhoneNumberUseCase,
    private val sortContactsUseCase: SortContactsUseCase
) {
    suspend operator fun invoke(): OperationResult<List<DomainContact>, String?> = try {
        withContext(Dispatchers.IO) {
            val rawContacts = contactRepository.getRawContacts()

            val contacts = rawContacts.mapNotNull { contact ->
                when (val formatPhoneNumberResult = formatPhoneNumberUseCase(contact.rawPhoneNumber)) {
                    is OperationResult.Success -> DomainContact(
                        photoUri = contact.photoUri,
                        name = contact.name,
                        phoneNumber = formatPhoneNumberResult.data
                    )
                    is OperationResult.Error -> null
                }
            }

            val sortedContacts = sortContactsUseCase(contacts)
            OperationResult.Success(sortedContacts)
        }
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        OperationResult.Error(e.message)
    }
}