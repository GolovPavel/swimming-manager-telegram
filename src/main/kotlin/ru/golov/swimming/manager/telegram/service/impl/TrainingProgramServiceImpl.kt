package ru.golov.swimming.manager.telegram.service.impl

import org.springframework.stereotype.Service
import ru.golov.swimming.manager.telegram.domain.TrainingProperties
import ru.golov.swimming.manager.telegram.entity.TrainingPropertiesEntity
import ru.golov.swimming.manager.telegram.entity.UserInfoEntity
import ru.golov.swimming.manager.telegram.repository.TrainingPropertiesRepository
import ru.golov.swimming.manager.telegram.service.TrainingProgramService
import ru.golov.swimming.manager.telegram.service.UserInfoService
import javax.transaction.Transactional

@Service
class TrainingProgramServiceImpl(
        val userInfoService: UserInfoService,
        val trainingPropertiesRepository: TrainingPropertiesRepository
) : TrainingProgramService {

    @Transactional
    override fun createNewTrainingProgram(userTelegramId: Long, trainingProperties: TrainingProperties): TrainingPropertiesEntity {
        val userInfoEntity = userInfoService.getOrCreateUserInfoByTelegramId(userTelegramId)
        userInfoService.changeUserNotificationFlag(userInfoEntity, true)

        disableUserTrainingPrograms(userInfoEntity)

        val trainingPropertiesEntity = TrainingPropertiesEntity(
                userInfoEntity,
                trainingProperties.trainingDistance,
                trainingProperties.trainingDifficulty,
                trainingProperties.trainingDays
        )

        return trainingPropertiesRepository.save(trainingPropertiesEntity)
    }

    private fun disableUserTrainingPrograms(userInfoEntity: UserInfoEntity) {
        val activeUserPrograms = trainingPropertiesRepository.findByTargetUserAndActiveTrue(userInfoEntity)
        activeUserPrograms.forEach { it.active = false }
        trainingPropertiesRepository.saveAll(activeUserPrograms)
    }
}