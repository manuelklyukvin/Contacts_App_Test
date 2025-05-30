package manuelklyukvin.contacts_app.main.phone.use_cases

import manuelklyukvin.contacts_app.core.utils.logger.models.Logger
import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.phone.adapters.PhoneAdapter
import kotlin.coroutines.cancellation.CancellationException

class MakeCallUseCase(
    private val phoneAdapter: PhoneAdapter,
    private val logger: Logger
) {
    operator fun invoke(phoneNumber: String) = try {
        phoneAdapter.makeCall(phoneNumber)
        OperationResult.Success(null)
    } catch (e: CancellationException) {
        logger.error("MakeCallUseCase: Cancellation", e)
        throw e
    } catch (e: Exception) {
        logger.error("MakeCallUseCase: Error with making call", e)
        OperationResult.Error(e.message)
    }
}