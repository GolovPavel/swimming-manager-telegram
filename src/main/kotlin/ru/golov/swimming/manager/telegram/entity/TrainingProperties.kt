package ru.golov.swimming.manager.telegram.entity

import java.time.DayOfWeek
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "training_properties")
class TrainingProperties(

        @ManyToOne
        @JoinColumn(name = "user_id")
        val targetUser: UserInfo,

        @Column(name = "training_distance", nullable = false)
        val trainingDistance: Int,

        @Column(name = "training_difficulty", nullable = false)
        @Enumerated(EnumType.STRING)
        val trainingDifficulty: TrainingDifficulty,

        @Enumerated(EnumType.STRING)
        @Column(name = "day_of_the_week")
        @ElementCollection(targetClass = DayOfWeek::class)
        @CollectionTable(name = "user_training_days", joinColumns = [JoinColumn(name = "user_info_id")])
        val trainingDays: List<DayOfWeek> = mutableListOf(),

        @Column(name = "is_active", nullable = false)
        val isActive: Boolean = true
) : BaseEntity<Long>()