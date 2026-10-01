package by.arsy.cleancodestudy.presentation

import androidx.lifecycle.ViewModel
import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase
import by.arsy.cleancodestudy.presentation.DriveBalanceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DriveBalanceViewModel @Inject constructor(
    private val updateUserBalanceByIdUseCase: UpdateUserBalanceByIdUseCase,
    private val getUserUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DriveBalanceState(balance = 0))
    val state = _state.asStateFlow()

    fun send(event: DriveBalanceEvent) {
        when (event) {
            is DriveBalanceEvent.GetEvent -> getUserBalance(event.userId)
            is DriveBalanceEvent.UpdateEvent -> updateUserBalance(
                userId = event.userId,
                difference = event.difference
            )
        }
    }

    private fun getUserBalance(userId: Long) {
        val user = getUserUseCase.execute(userId)
        _state.value = _state.value.copy(balance = user.balance)
    }

    private fun updateUserBalance(userId: Long, difference: Int) {
        updateUserBalanceByIdUseCase.execute(
            userId = userId,
            difference = difference
        )
    }

}