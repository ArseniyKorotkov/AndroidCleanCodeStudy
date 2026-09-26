package by.arsy.cleancodestudy.domain.repository

import by.arsy.cleancodestudy.domain.model.User

interface UserRepository {

    fun getUserById(userId: Long): User

    fun updateUserBalanceById(userId: Long, difference: Int): Int

}