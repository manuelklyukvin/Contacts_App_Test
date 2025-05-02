package manuelklyukvin.contacts_app.main.use_cases

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.repositories.ContactRepository

class GetContactsUseCase(private val contactRepository: ContactRepository) {
    suspend operator fun invoke() = try {
        withContext(Dispatchers.IO) {
            OperationResult.Success(contactRepository.getContacts())
        }
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        OperationResult.Error(e.localizedMessage)
    }
}