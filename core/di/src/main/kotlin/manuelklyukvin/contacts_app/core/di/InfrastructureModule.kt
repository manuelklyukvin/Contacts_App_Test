package manuelklyukvin.contacts_app.core.di

import manuelklyukvin.contacts_app.core.utils.logger.models.Logger
import manuelklyukvin.contacts_app.core.utils.logger.models.LoggerImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val infrastructureModule = module { singleOf(::LoggerImpl) bind Logger::class }