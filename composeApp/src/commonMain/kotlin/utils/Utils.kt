package utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime



fun getCurrentDate() : LocalDate {
    val now: Instant = Clock.System.now()
    return now.toLocalDateTime(TimeZone.currentSystemDefault()).date
}

fun localDateChecker (date : LocalDate) : String {

    val dateFormat = LocalDate.Format {
        dayOfMonth(Padding.ZERO)
        char('/')
        monthNumber(Padding.ZERO)
        char('/')
        yearTwoDigits(1960)
    }
    val dateString = date.format(dateFormat)

    val today = getCurrentDate()
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