package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var amount : Double,
    val date : Long
)



