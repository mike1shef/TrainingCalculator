package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import database.model.BodyMeasurements
import database.model.Event
import database.model.Payment
import database.model.Weight

@Database(
    entities = [Event::class, Payment::class, BodyMeasurements::class, Weight::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class TrainingCalculatorDatabase : RoomDatabase() {
    abstract fun trainingsDAO() : TrainingsDAO
}