package by.arsy.cleancodestudy.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.arsy.cleancodestudy.data.repository.UserRepositoryImpl
import by.arsy.cleancodestudy.data.storage.SharedPreferencesUserStorage
import by.arsy.cleancodestudy.domain.repository.UserRepository
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase

class DriveBalanceViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val sharedPreferencesUserStorage: SharedPreferencesUserStorage by lazy {
        SharedPreferencesUserStorage(context = context)
    }

    private val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userStorage = sharedPreferencesUserStorage)
    }
    private val updateUserBalanceByIdUseCase by lazy { UpdateUserBalanceByIdUseCase(userRepository) }
    private val getUserUseCase by lazy { GetUserByIdUseCase(userRepository) }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DriveBalanceViewModel(
            updateUserBalanceByIdUseCase = updateUserBalanceByIdUseCase,
            getUserUseCase = getUserUseCase
        ) as T
    }
}