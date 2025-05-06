package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.contacts.repositories.RawContactRepository
import manuelklyukvin.contacts_app.main.contacts.repositories.RawContactRepositoryImpl
import manuelklyukvin.contacts_app.main.phone.repositoies.PhoneNumberRepositoryImpl
import manuelklyukvin.contacts_app.main.phone.repositories.PhoneNumberRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModule = module {
    singleOf(::RawContactRepositoryImpl) bind RawContactRepository::class
    singleOf(::PhoneNumberRepositoryImpl) bind PhoneNumberRepository::class
}