package ru.golov.swimming.manager.telegram.entity

import java.time.DayOfWeek
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embeddable
import javax.persistence.Enumerated

@Embeddable
class TrainingProperties(

        @Column(name = "training_distance")
        val trainingDistance: Int,

        @Column(name = "training_difficulty")
        val trainingDifficulty: Int,

        @Column
        @Enumerated
        @ElementCollection(targetClass = DayOfWeek::class)
        val trainingDays: List<DayOfWeek> = mutableListOf()
)