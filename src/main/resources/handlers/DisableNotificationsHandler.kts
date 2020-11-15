import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.MarkdownMessage
import ru.golov.swimming.manager.telegram.service.UserInfoService

handler("/disableNotifications") {
    process { stats, _ ->
        val userInfoService = getBean<UserInfoService>()
        val userInfo = userInfoService.getOrCreateUserInfoByTelegramId(stats.chat.id)

        if (userInfo.isNotificationActive) {
            userInfoService.changeUserNotificationFlag(userInfo, false)
            MarkdownMessage("I turned off notifications about training for you.")
        } else {
            MarkdownMessage("Training notifications have already been disabled.")
        }
    }
}