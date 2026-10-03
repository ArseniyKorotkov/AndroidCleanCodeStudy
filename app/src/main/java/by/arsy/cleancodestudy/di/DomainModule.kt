package by.arsy.cleancodestudy.di

import by.arsy.cleancodestudy.domain.repository.UserRepository
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class DomainModule {

    @Provides
    fun provideUpdateUserBalanceByIdUseCase(userRepository: UserRepository): UpdateUserBalanceByIdUseCase {
        return UpdateUserBalanceByIdUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideGetUserByIdUseCase(userRepository: UserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(userRepository = userRepository)
    }

}