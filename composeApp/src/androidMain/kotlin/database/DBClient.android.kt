package database

import android.content.Context
import androidx.room.Room

actual class DBClient (context: Context) {
    actual val database = getDatabase(context)

    private fun getDatabase(ctx : Context) : TrainingCalculatorDatabase{
        val context = ctx.applicationContext
        val dbPath = context.getDatabasePath("TrainingCalculatorDatabase.db")
        return Room.databaseBuilder<TrainingCalculatorDatabase>(
            context = context,
            name = dbPath.absolutePath
        ).build()
    }
}