package manuelklyukvin.contacts_app.main.phone.use_cases

import manuelklyukvin.contacts_app.core.utils.logger.models.Logger
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.phone.repositories.PhoneNumberRepository
import kotlin.coroutines.cancellation.CancellationException

class GetPhoneNumberUseCase(
    private val phoneNumberRepository: PhoneNumberRepository,
    private val formatPhoneNumberUseCase: FormatPhoneNumberUseCase,
    private val logger: Logger
) {
    suspend operator fun invoke(contactId: Int) = try {
        val rawPhoneNumber = phoneNumberRepository.getPhoneNumber(contactId)
        if (!rawPhoneNumber.isNullOrBlank()) {
            formatPhoneNumberUseCase(rawPhoneNumber)
        } else {
            OperationResult.Error("Phone number not found")
        }
    } catch (e: CancellationException) {
        logger.error("GetPhoneNumberUseCase: Cancellation", e)
        throw e
    } catch (e: Exception) {
        logger.error("GetPhoneNumberUseCase: Error fetching phone number", e)
        OperationResult.Error(e.message)
    }
}