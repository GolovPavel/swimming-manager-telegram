package ru.golov.swimming.manager.telegram.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.golov.swimming.manager.telegram.entity.UserInfo

interface UserInfoRepository : JpaRepository<UserInfo, Long> {

    fun findByTelegramId(telegramId: Long)
}