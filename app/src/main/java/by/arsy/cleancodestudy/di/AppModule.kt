package by.arsy.cleancodestudy.di

import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import by.arsy.cleancodestudy.presentation.DriveBalancePresenter
import by.arsy.cleancodestudy.presentation.DriveBalancePresenterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Provides
    fun provideDriveBalancePresenter(
        updateUserBalanceByIdUseCase: UpdateUserBalanceByIdUseCase,
        getUserUseCase: GetUserByIdUseCase
    ): DriveBalancePresenter {
        return DriveBalancePresenterImpl(
            updateUserBalanceByIdUseCase = updateUserBalanceByIdUseCase,
            getUserUseCase = getUserUseCase
        )
    }
}