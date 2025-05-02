package manuelklyukvin.contacts_app.main.di

import org.koin.dsl.module

val mainModule = module { includes(presentationModule, domainModule, dataModule) }