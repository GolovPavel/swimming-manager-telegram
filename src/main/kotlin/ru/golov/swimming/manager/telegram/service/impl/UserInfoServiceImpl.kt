package ru.golov.swimming.manager.telegram.service.impl

import org.springframework.stereotype.Service
import ru.golov.swimming.manager.telegram.entity.UserInfoEntity
import ru.golov.swimming.manager.telegram.repository.UserInfoRepository
import ru.golov.swimming.manager.telegram.service.UserInfoService

@Service
class UserInfoServiceImpl(val userInfoRepository: UserInfoRepository) : UserInfoService {

    override fun getOrCreateUserInfoByTelegramId(userTelegramId: Long): UserInfoEntity {
        return userInfoRepository.findByTelegramId(userTelegramId) ?: createUserByTelegramId(userTelegramId)
    }

    override fun changeUserNotificationFlag(userInfoEntity: UserInfoEntity, isNotificationActive: Boolean) {
        userInfoEntity.isNotificationActive = isNotificationActive
        saveUserInfo(userInfoEntity)
    }

    private fun createUserByTelegramId(telegramId: Long): UserInfoEntity {
        val userInfo = UserInfoEntity(telegramId)
        return saveUserInfo(userInfo)
    }

    private fun saveUserInfo(userInfoEntity: UserInfoEntity): UserInfoEntity {
        return userInfoRepository.save(userInfoEntity)
    }
}