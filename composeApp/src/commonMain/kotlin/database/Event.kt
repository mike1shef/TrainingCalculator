package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String = "Training",
    var isPaid: Boolean,
    val date : String
)

fun generateEvents() : List<Event>{
    return listOf(
        Event(isPaid = false, date = "31/06/2024"),
        Event(isPaid = false, date = "29/06/2024"),
        Event(isPaid = false, date = "15/06/2024"),
        Event(isPaid = true, date = "14/06/2024")
    )
}
