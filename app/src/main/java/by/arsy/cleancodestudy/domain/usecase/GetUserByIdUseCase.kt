package by.arsy.cleancodestudy.domain.usecase

import by.arsy.cleancodestudy.domain.model.User

class GetUserByIdUseCase {

    fun execute(userId: Long): User {
        // TODO: now return stub data
        return User(userId, 100)
    }

}