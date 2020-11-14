package ru.golov.swimming.manager.telegram.service

import ru.golov.swimming.manager.telegram.domain.TrainingProperties
import ru.golov.swimming.manager.telegram.entity.TrainingPropertiesEntity

interface TrainingProgramService {

    fun createNewTrainingProgram(userTelegramId: Long, trainingProperties: TrainingProperties): TrainingPropertiesEntity
}