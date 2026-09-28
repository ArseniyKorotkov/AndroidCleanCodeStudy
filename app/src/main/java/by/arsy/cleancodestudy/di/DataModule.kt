package by.arsy.cleancodestudy.di

import by.arsy.cleancodestudy.data.repository.UserRepositoryImpl
import by.arsy.cleancodestudy.data.storage.SharedPreferencesUserStorage
import by.arsy.cleancodestudy.data.storage.UserStorage
import by.arsy.cleancodestudy.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    factory<UserStorage> {
        SharedPreferencesUserStorage(context = get())
    }

    factory<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }

}