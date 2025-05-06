package manuelklyukvin.contacts_app.main.contacts.use_cases

import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.contacts.models.DomainContact
import manuelklyukvin.contacts_app.main.contacts.repositories.RawContactRepository
import manuelklyukvin.contacts_app.main.phone.use_cases.GetPhoneNumberUseCase

class GetContactsUseCase(
    private val rawContactRepository: RawContactRepository,
    private val getPhoneNumberUseCase: GetPhoneNumberUseCase
) {
    suspend operator fun invoke(): List<DomainContact> {
        val rawContacts = rawContactRepository.getRawContacts()
        return rawContacts.mapNotNull { rawContact ->
            when (val getPhoneNumberResult = getPhoneNumberUseCase(rawContact.id)) {
                is OperationResult.Success -> DomainContact(
                    id = rawContact.id,
                    photoUri = rawContact.photoUri,
                    name = rawContact.name,
                    phoneNumber = getPhoneNumberResult.data
                )
                is OperationResult.Error -> null
            }
        }
    }
}