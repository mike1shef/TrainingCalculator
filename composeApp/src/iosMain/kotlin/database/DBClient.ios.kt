package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

actual class DBClient {
    actual val database: TrainingCalculatorDatabase = getDatabase()

    private fun getDatabase(): TrainingCalculatorDatabase {
        val dbFilePath = NSHomeDirectory() + "/my_room.db"
        return Room.databaseBuilder<TrainingCalculatorDatabase>(
            name = dbFilePath,
            factory =  { TrainingCalculatorDatabase::class.instantiateImpl() }
            ).setDriver(BundledSQLiteDriver())
                .build()
        }
    }