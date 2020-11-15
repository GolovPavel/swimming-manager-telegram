package ru.golov.swimming.manager.telegram.service

import ru.golov.swimming.manager.telegram.entity.UserInfoEntity

interface UserInfoService {

    fun getOrCreateUserInfoByTelegramId(userTelegramId: Long): UserInfoEntity

    fun changeUserNotificationFlag(userInfoEntity: UserInfoEntity, isNotificationActive: Boolean)
}