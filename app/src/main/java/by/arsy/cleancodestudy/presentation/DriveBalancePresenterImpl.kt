package by.arsy.cleancodestudy.presentation

import by.arsy.cleancodestudy.domain.usecase.GetUserByIdUseCase
import by.arsy.cleancodestudy.domain.usecase.UpdateUserBalanceByIdUseCase

class DriveBalancePresenterImpl(
    private val updateUserBalanceByIdUseCase: UpdateUserBalanceByIdUseCase,
    private val getUserUseCase: GetUserByIdUseCase
) : DriveBalancePresenter {

    private var view: DriveBalanceView? = null

    override fun getUserBalance(userId: Long) {
        val user = getUserUseCase.execute(userId)
        view?.showBalance(balance = user.balance)
    }

    override fun updateUserBalance(userId: Long, difference: Int) {
        updateUserBalanceByIdUseCase.execute(
            userId = userId,
            difference = difference
        )
    }

    override fun bindView(view: DriveBalanceView) {
        this.view = view
    }

    override fun unbindView() {
        this.view = null
    }


}