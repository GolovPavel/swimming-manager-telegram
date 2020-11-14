package ru.golov.swimming.manager.telegram.domain

import ru.golov.swimming.manager.telegram.entity.TrainingDifficulty
import java.time.DayOfWeek

data class TrainingProperties(
        val trainingDistance: Int,
        val trainingDifficulty: TrainingDifficulty,
        val trainingDays: List<DayOfWeek>
)