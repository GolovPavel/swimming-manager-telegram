package ru.golov.swimming.manager.telegram.repository

import org.springframework.data.repository.CrudRepository
import ru.golov.swimming.manager.telegram.entity.UserInfoEntity

interface UserInfoRepository : CrudRepository<UserInfoEntity, Long> {

    fun findByTelegramId(telegramId: Long): UserInfoEntity?
}