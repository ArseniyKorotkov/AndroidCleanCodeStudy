package by.arsy.cleancodestudy.presentation

sealed interface DriveBalanceEvent {

    data class GetEvent(
        val userId: Long
    ) : DriveBalanceEvent

    data class UpdateEvent(
        val userId: Long,
        val difference: Int
    ) : DriveBalanceEvent

}