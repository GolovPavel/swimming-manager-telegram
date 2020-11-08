import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.MarkdownMessage

handler("/help") {
    process { _, _ ->
        val message = """
            /newTrainingProgram - _Create new training program._
            /getTrainingTasks - _Get information about nearest training._
            /completeTraining - _Complete training ahead of schedule._
            /disableNotifications - _Disable notifications on the day of training._
            /stats - _Get your training stats._
            /help - _Get command list._
        """.trimIndent()

        MarkdownMessage(message)
    }
}