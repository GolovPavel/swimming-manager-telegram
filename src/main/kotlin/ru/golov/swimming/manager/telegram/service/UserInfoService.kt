package ru.golov.swimming.manager.telegram.service

import org.springframework.stereotype.Service
import ru.golov.swimming.manager.telegram.entity.UserInfo
import ru.golov.swimming.manager.telegram.repository.UserInfoRepository

@Service
class UserInfoService(val userInfoRepository: UserInfoRepository) {

    fun saveUserInfo(userInfo: UserInfo): UserInfo {
        return userInfoRepository.save(userInfo)
    }

    fun getUserInfoByTelegramId(telegramId: Long) {
        return userInfoRepository.findByTelegramId(telegramId)
    }
}