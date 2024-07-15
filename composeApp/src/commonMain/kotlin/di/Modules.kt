package di

import database.DBClient
import database.RepositoryImpl
import org.koin.core.module.Module

import org.koin.dsl.module

expect val platformModule : Module

val sharedModules = module {
    single {
        get<DBClient>().database.trainingsDAO()
    }
    single {
        RepositoryImpl(get())
    }

}
