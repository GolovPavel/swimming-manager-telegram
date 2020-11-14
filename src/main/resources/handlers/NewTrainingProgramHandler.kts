package handlers

import me.ruslanys.telegraff.core.dsl.handler
import me.ruslanys.telegraff.core.dto.request.MarkdownMessage
import me.ruslanys.telegraff.core.exception.ValidationException
import ru.golov.swimming.manager.telegram.domain.TrainingProperties
import ru.golov.swimming.manager.telegram.entity.TrainingDifficulty
import ru.golov.swimming.manager.telegram.service.TrainingProgramService
import java.lang.NumberFormatException
import java.time.DayOfWeek


handler("/newTrainingProgram") {
    step<Int>("distance") {
        question {
            MarkdownMessage("What distance in meters do you usually swim in training?")
        }

        validation {
            try {
                Integer.parseInt(it)
            } catch (e: NumberFormatException) {
                throw ValidationException("Please, type a valid number.")
            }
        }
    }

    step<TrainingDifficulty>("trainingDifficulty") {
        question {
            MarkdownMessage("What is your training difficulty?", "Low", "Medium", "Hard")
        }

        validation {
            when (it.toLowerCase()) {
                "low" -> TrainingDifficulty.LOW
                "medium" -> TrainingDifficulty.MEDIUM
                "hard" -> TrainingDifficulty.HARD
                else -> throw ValidationException("Please, select one of the suggested options.")
            }
        }
    }

    step<List<DayOfWeek>>("daysOfWeek") {
        question {
            MarkdownMessage("What days of the week do you plan to train?")
        }

        validation {
            it.split(" ").map { dayOfWeekString ->
                when (dayOfWeekString.toLowerCase()) {
                    "monday" -> DayOfWeek.MONDAY
                    "tuesday" -> DayOfWeek.TUESDAY
                    "wednesday" -> DayOfWeek.WEDNESDAY
                    "thursday" -> DayOfWeek.THURSDAY
                    "friday" -> DayOfWeek.FRIDAY
                    "saturday" -> DayOfWeek.SATURDAY
                    "sunday" -> DayOfWeek.SUNDAY
                    else -> throw ValidationException("Wrong day of the week name: $dayOfWeekString")
                }
            }
        }
    }

    process { stats, answers ->
        val trainingDistance = answers["distance"] as Int
        val trainingDifficulty = answers["trainingDifficulty"] as TrainingDifficulty
        val trainingDays = answers["daysOfWeek"] as List<DayOfWeek>

        val trainingProperties = TrainingProperties(
                trainingDistance,
                trainingDifficulty,
                trainingDays
        )

        val trainingProgramService = getBean<TrainingProgramService>()
        trainingProgramService.createNewTrainingProgram(stats.chat.id, trainingProperties)

        MarkdownMessage("""
            Training program created!
            Average training distance: $trainingDistance;
            Training difficulty: $trainingDifficulty;
            Training days: $trainingDays
            
            I will notify you on the day of training.
        """.trimIndent())
    }
}