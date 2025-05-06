package manuelklyukvin.contacts_app.core.di

import org.koin.dsl.module

val coreModule = module { includes(dataModule) }