package database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class Converters {
    @TypeConverter
    fun fromEpoch(value: Int?): LocalDate? {
        return value?.let { LocalDate.fromEpochDays(value) }
    }

    @TypeConverter
    fun dateToEpoch(date: LocalDate?): Int? {
        return date?.toEpochDays()
    }
}