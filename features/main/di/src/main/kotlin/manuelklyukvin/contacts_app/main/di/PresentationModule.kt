package manuelklyukvin.contacts_app.main.di

import manuelklyukvin.contacts_app.main.ui.view_models.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module { viewModelOf(::MainViewModel) }