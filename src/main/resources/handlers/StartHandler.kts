import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.MarkdownMessage

handler("/start") {
    process { _, _ ->
        val message = """
            Hi! I'm a swimming manager telegram bot.
            I can create a training program for you and notify you about training.
            Please type /help to find out what I can do.
        """.trimIndent()

        MarkdownMessage(message)
    }
}