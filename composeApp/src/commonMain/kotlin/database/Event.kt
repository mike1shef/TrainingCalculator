package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String = "Training",
    var status: EventStatus,
    val date : String
)

fun generateEvents() : List<Event>{
    return listOf(
        Event(status = EventStatus.UPCOMING, date = "31/06/2024"),
        Event(status = EventStatus.UPCOMING, date = "29/06/2024"),
        Event(status = EventStatus.PAID, date = "15/06/2024"),
        Event(status = EventStatus.PAID, date = "14/06/2024")
    )
}

enum class EventStatus {
    UPCOMING,
    PAID
}