package by.arsy.cleancodestudy.data.storage

import by.arsy.cleancodestudy.data.storage.entity.UserEntity

interface UserStorage {

    fun getUser(id: Long): UserEntity

    fun updateUser(user: UserEntity)

}