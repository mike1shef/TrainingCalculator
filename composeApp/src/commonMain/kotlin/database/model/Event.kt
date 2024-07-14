package database.model

import androidx.compose.runtime.LaunchedEffect
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String = "Training",
    val cost : Double = 30.0,
    var isPaid: Boolean,
    val date : String
)
