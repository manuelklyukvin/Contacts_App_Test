package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.contacts.repositories.RawContactRepository
import manuelklyukvin.contacts_app.main.contacts.repositories.RawContactRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModule = module { singleOf(::RawContactRepositoryImpl) bind RawContactRepository::class }