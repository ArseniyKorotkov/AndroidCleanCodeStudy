package by.arsy.cleancodestudy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase

class DriveBalanceViewModelFactory(
    private val updateUserBalanceByIdUseCase: UpdateUserBalanceByIdUseCase,
    private val getUserUseCase: GetUserByIdUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DriveBalanceViewModel(
            updateUserBalanceByIdUseCase = updateUserBalanceByIdUseCase,
            getUserUseCase = getUserUseCase
        ) as T
    }
}