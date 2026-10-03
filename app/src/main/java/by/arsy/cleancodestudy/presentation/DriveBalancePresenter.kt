package by.arsy.cleancodestudy.presentation

interface DriveBalancePresenter {

    fun getUserBalance(userId: Long)

    fun updateUserBalance(userId: Long, difference: Int)

    fun bindView(view: DriveBalanceView)

    fun unbindView()
}