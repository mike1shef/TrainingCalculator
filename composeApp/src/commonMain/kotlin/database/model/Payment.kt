package database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var amount : Double,
    val date : LocalDate
)



