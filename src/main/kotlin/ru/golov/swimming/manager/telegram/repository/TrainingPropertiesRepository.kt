package ru.golov.swimming.manager.telegram.repository

import org.springframework.data.repository.CrudRepository
import ru.golov.swimming.manager.telegram.entity.TrainingPropertiesEntity
import ru.golov.swimming.manager.telegram.entity.UserInfoEntity

interface TrainingPropertiesRepository : CrudRepository<TrainingPropertiesEntity, Long> {

    fun findByTargetUserAndActiveTrue(targetUser: UserInfoEntity): Iterable<TrainingPropertiesEntity>
}