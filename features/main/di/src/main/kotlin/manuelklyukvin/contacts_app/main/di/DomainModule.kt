package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.contacts.use_cases.GetContactGroupsUseCase
import manuelklyukvin.contacts_app.main.contacts.use_cases.GetContactsUseCase
import manuelklyukvin.contacts_app.main.phone.use_cases.FormatPhoneNumberUseCase
import manuelklyukvin.contacts_app.main.phone.use_cases.MakeCallUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainModule = module {
    singleOf(::GetContactGroupsUseCase)
    singleOf(::GetContactsUseCase)
    singleOf(::FormatPhoneNumberUseCase)

    singleOf(::MakeCallUseCase)
}