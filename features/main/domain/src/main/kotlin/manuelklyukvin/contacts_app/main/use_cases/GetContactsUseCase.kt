package manuelklyukvin.contacts_app.main.use_cases

import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.models.DomainContact
import manuelklyukvin.contacts_app.main.models.DomainRawContact
import manuelklyukvin.contacts_app.main.repositories.RawContactRepository

class GetContactsUseCase(
    private val rawContactRepository: RawContactRepository,
    private val formatPhoneNumberUseCase: FormatPhoneNumberUseCase,
) {
    suspend operator fun invoke(): List<DomainContact> {
        val rawContacts = rawContactRepository.getRawContacts()
        return rawContacts.mapNotNull { formatRawContact(it) }
    }

    private fun formatRawContact(rawContact: DomainRawContact): DomainContact? {
        if (rawContact.name.isBlank()) return null
        return when (val formatPhoneNumberResult = formatPhoneNumberUseCase(rawContact.rawPhoneNumber)) {
            is OperationResult.Success -> DomainContact(
                photoUri = rawContact.photoUri,
                name = rawContact.name.trim(),
                phoneNumber = formatPhoneNumberResult.data
            )
            is OperationResult.Error -> null
        }
    }
}