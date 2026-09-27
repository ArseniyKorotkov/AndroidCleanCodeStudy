package by.arsy.cleancodestudy.data.repository

import by.arsy.cleancodestudy.data.storage.UserStorage
import by.arsy.cleancodestudy.data.storage.entity.UserEntity
import by.arsy.cleancodestudy.domain.model.User
import by.arsy.cleancodestudy.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun getUserById(userId: Long): User {
        return map(userEntity = userStorage.getUser(userId))
    }

    override fun updateUserBalanceById(userId: Long, difference: Int): Int {
        val user = getUserById(userId)
        val newBalance = user.balance + difference
        userStorage.updateUser(user = map(user.copy(balance = newBalance)))
        return newBalance
    }

    private fun map(user: User) : UserEntity {
        return UserEntity(
            id = user.id,
            balance = user.balance
        )
    }

    private fun map(userEntity: UserEntity) : User {
        return User(
            id = userEntity.id,
            balance = userEntity.balance
        )
    }
}