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
class TrainingPropertiesEntity(

        @ManyToOne
        @JoinColumn(name = "user_info_id")
        val targetUser: UserInfoEntity,

        @Column(name = "training_distance", nullable = false)
        val trainingDistance: Int,

        @Enumerated
        @Column(name = "training_difficulty", nullable = false)
        val trainingDifficulty: TrainingDifficulty,

        @Enumerated(EnumType.STRING)
        @Column(name = "day_of_the_week")
        @ElementCollection(targetClass = DayOfWeek::class)
        @CollectionTable(name = "user_training_days", joinColumns = [JoinColumn(name = "training_properties_id")])
        val trainingDays: List<DayOfWeek> = mutableListOf(),

        @Column(name = "active", nullable = false)
        var active: Boolean = true
) : BaseEntity<Long>()