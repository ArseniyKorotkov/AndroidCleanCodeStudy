package by.arsy.cleancodestudy.data.storage

import android.content.Context
import by.arsy.cleancodestudy.data.storage.entity.UserEntity

class SharedPreferencesUserStorage(context: Context) : UserStorage {

    private val sharedPreferences = context.getSharedPreferences(
        PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    override fun getUser(id: Long): UserEntity {
        val balance = sharedPreferences.getInt(id.toString(), 0)
        return UserEntity(
            id = id,
            balance = balance
        )
    }

    override fun updateUser(user: UserEntity) {
        sharedPreferences.edit().putInt(
            user.id.toString(),
            user.balance
        ).apply()
    }


    companion object {
        private const val PREFERENCE_NAME = "preference_name"
    }
}