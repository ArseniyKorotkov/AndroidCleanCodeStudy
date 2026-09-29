package by.arsy.cleancodestudy.di

import android.content.Context
import by.arsy.cleancodestudy.data.repository.UserRepositoryImpl
import by.arsy.cleancodestudy.data.storage.SharedPreferencesUserStorage
import by.arsy.cleancodestudy.data.storage.UserStorage
import by.arsy.cleancodestudy.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideUserStorage(context: Context): UserStorage {
        return SharedPreferencesUserStorage(context = context)
    }

    @Provides
    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }

}