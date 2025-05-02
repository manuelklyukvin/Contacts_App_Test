package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.data_sources.AndroidContactDataSource
import manuelklyukvin.contacts_app.main.data_sources.ContactDataSource
import manuelklyukvin.contacts_app.main.repositories.ContactRepository
import manuelklyukvin.contacts_app.main.repositories.ContactRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModule = module {
    singleOf(::AndroidContactDataSource) bind ContactDataSource::class
    singleOf(::ContactRepositoryImpl) bind ContactRepository::class
}