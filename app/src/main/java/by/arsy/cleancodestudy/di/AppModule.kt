package by.arsy.cleancodestudy.di

import android.content.Context
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import by.arsy.cleancodestudy.presentation.DriveBalanceViewModel
import by.arsy.cleancodestudy.presentation.DriveBalanceViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context)  {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideDriveBalanceViewModel(
        updateUserBalanceByIdUseCase: UpdateUserBalanceByIdUseCase,
        getUserUseCase: GetUserByIdUseCase
    ): DriveBalanceViewModelFactory {
        return DriveBalanceViewModelFactory(
            updateUserBalanceByIdUseCase = updateUserBalanceByIdUseCase,
            getUserUseCase = getUserUseCase
        )
    }
}