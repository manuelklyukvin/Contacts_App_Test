package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.adapters.AndroidPhoneAdapter
import manuelklyukvin.contacts_app.main.adapters.PhoneAdapter
import manuelklyukvin.contacts_app.main.data_sources.AndroidRawContactDataSource
import manuelklyukvin.contacts_app.main.data_sources.RawContactDataSource
import manuelklyukvin.contacts_app.main.repositories.RawContactRepository
import manuelklyukvin.contacts_app.main.repositories.RawContactRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModule = module {
    singleOf(::AndroidRawContactDataSource) bind RawContactDataSource::class
    singleOf(::RawContactRepositoryImpl) bind RawContactRepository::class

    singleOf(::AndroidPhoneAdapter) bind PhoneAdapter::class
}