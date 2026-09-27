package by.arsy.cleancodestudy.presentation

import androidx.lifecycle.ViewModel
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DriveBalanceViewModel(
    private val updateUserBalanceByIdUseCase: UpdateUserBalanceByIdUseCase,
    private val getUserUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _balance = MutableStateFlow(0)
    val balance = _balance.asStateFlow()

    fun getUserBalance(userId: Long) {
        val user = getUserUseCase.execute(userId)
        _balance.value = user.balance
    }

    fun updateUserBalance(userId: Long, difference: Int) {
        updateUserBalanceByIdUseCase.execute(
            userId = userId,
            difference = difference
        )
    }

}