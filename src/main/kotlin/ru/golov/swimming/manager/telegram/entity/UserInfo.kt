package ru.golov.swimming.manager.telegram.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user_info")
class UserInfo(

        @Column(name = "telegram_id", unique = true)
        val telegramId: Long,

        @Column(name = "is_notification_active")
        val isNotificationActive: Boolean
) : BaseEntity<Long>()