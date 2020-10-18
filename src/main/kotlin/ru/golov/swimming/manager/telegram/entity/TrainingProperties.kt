package ru.golov.swimming.manager.telegram.entity

import java.time.DayOfWeek
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn

@Embeddable
class TrainingProperties(

        @Column(name = "training_distance")
        val trainingDistance: Int,

        @Column(name = "training_difficulty")
        @Enumerated(EnumType.STRING)
        val trainingDifficulty: TrainingDifficulty,

        @Enumerated(EnumType.STRING)
        @Column(name = "day_of_the_week")
        @ElementCollection(targetClass = DayOfWeek::class)
        @CollectionTable(name = "user_training_days", joinColumns = [JoinColumn(name = "user_info_id")])
        val trainingDays: List<DayOfWeek> = mutableListOf()
)