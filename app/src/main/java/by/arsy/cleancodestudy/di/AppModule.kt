package by.arsy.cleancodestudy.di

import by.arsy.cleancodestudy.presentation.DriveBalanceViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<DriveBalanceViewModel> {
        DriveBalanceViewModel(
            updateUserBalanceByIdUseCase = get(),
            getUserUseCase = get()
        )
    }
}