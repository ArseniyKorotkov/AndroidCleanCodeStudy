package by.arsy.cleancodestudy.data.repository

import android.content.Context
import by.arsy.cleancodestudy.domain.model.User
import by.arsy.cleancodestudy.domain.repository.UserRepository

class UserRepositoryImpl(context: Context) : UserRepository {

    private val sharedPreferences = context.getSharedPreferences(
        PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    override fun getUserById(userId: Long): User {
        val balance = sharedPreferences.getInt(userId.toString(), 0)
        return User(
            id = userId,
            balance = balance
        )
    }

    override fun updateUserBalanceById(userId: Long, difference: Int): Int {
        val user = getUserById(userId)
        val newBalance = user.balance + difference
        sharedPreferences.edit().putInt(user.id.toString(), newBalance).apply()
        return newBalance
    }

    companion object {
        private const val PREFERENCE_NAME = "preference_name"
    }
}