package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.*

actual class DBClient {
    actual val database: TrainingCalculatorDatabase = getDatabase()

    private fun getDatabase(): TrainingCalculatorDatabase {
        val dbFilePath = "${fileDirectory()}/my_room.db"

        return Room.databaseBuilder<TrainingCalculatorDatabase>(
            name = dbFilePath,
            factory =  { TrainingCalculatorDatabase::class.instantiateImpl() }
            )
            .addMigrations()
            .fallbackToDestructiveMigrationOnDowngrade(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }

@OptIn(ExperimentalForeignApi::class)
private fun fileDirectory(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory).path!!
}