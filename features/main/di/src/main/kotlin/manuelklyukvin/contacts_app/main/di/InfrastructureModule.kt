package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.contacts.data_sources.RawContactDataSource
import manuelklyukvin.contacts_app.main.contacts.data_sources.RawContactDataSourceImpl
import manuelklyukvin.contacts_app.main.phone.adapters.PhoneAdapter
import manuelklyukvin.contacts_app.main.phone.adapters.PhoneAdapterImpl
import manuelklyukvin.contacts_app.main.phone.data_sources.PhoneNumberDataSource
import manuelklyukvin.contacts_app.main.phone.data_sources.PhoneNumberDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val infrastructureModule = module {
    singleOf(::RawContactDataSourceImpl) bind RawContactDataSource::class
    singleOf(::PhoneNumberDataSourceImpl) bind PhoneNumberDataSource::class

    singleOf(::PhoneAdapterImpl) bind PhoneAdapter::class
}