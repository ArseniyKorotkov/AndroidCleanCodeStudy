package by.arsy.cleancodestudy.domain.usecase

import by.arsy.cleancodestudy.domain.repository.UserRepository

class UpdateUserBalanceByIdUseCase(private val userRepository: UserRepository) {

    fun execute(userId: Long, difference: Int): Int {
        return userRepository.updateUserBalanceById(
            userId = userId,
            difference = difference
        )
    }

}