package utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime


fun localDateChecker (date : LocalDate) : String {

    val dateFormat = LocalDate.Format {
        dayOfMonth(padding = Padding.SPACE)
        char(' ')
        monthNumber()
        char(' ')
        dayOfMonth()
    }
    val dateString = date.format(dateFormat)

    val now: Instant = Clock.System.now()
    val today: LocalDate = now.toLocalDateTime(TimeZone.currentSystemDefault()).date
    val yesterday = today.minus(1, DateTimeUnit.DAY)
    val tomorrow = today.plus(1, DateTimeUnit.DAY)


    return when (date) {
        today -> {
            "Today"
        }
        yesterday -> {
            "Yesterday"
        }
        tomorrow -> {
            "Tomorrow"
        }
        else -> {
            dateString
        }
    }
}