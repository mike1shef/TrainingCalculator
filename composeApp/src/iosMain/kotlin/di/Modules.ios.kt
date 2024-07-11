package di

import MainViewModel
import database.DBClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::DBClient)
    single {
        MainViewModel(get())
    }
}