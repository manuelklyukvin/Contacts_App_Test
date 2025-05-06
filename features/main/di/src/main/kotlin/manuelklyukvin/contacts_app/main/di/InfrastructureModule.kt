package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.contacts.data_sources.AndroidRawContactDataSourceImpl
import manuelklyukvin.contacts_app.main.contacts.data_sources.RawContactDataSource
import manuelklyukvin.contacts_app.main.phone.adapters.AndroidPhoneAdapterImpl
import manuelklyukvin.contacts_app.main.phone.adapters.PhoneAdapter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val infrastructureModule = module {
    singleOf(::AndroidRawContactDataSourceImpl) bind RawContactDataSource::class
    singleOf(::AndroidPhoneAdapterImpl) bind PhoneAdapter::class
}