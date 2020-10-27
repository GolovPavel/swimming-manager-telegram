package ru.golov.swimming.manager.telegram.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.golov.swimming.manager.telegram.entity.TrainingProperties
import ru.golov.swimming.manager.telegram.entity.UserInfo
import java.util.Optional

interface TrainingPropertiesRepository : JpaRepository<TrainingProperties, Long> {

    fun findByTargetUser(targetUser: UserInfo): Optional<TrainingProperties>
}