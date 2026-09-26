package by.arsy.cleancodestudy.domain.usecase

import by.arsy.cleancodestudy.domain.model.User
import by.arsy.cleancodestudy.domain.repository.UserRepository

class GetUserByIdUseCase(private val userRepository: UserRepository) {

    fun execute(userId: Long): User {
        return userRepository.getUserById(userId)
    }

}