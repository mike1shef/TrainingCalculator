package database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BodyMeasurements (
    @PrimaryKey(autoGenerate = true)  val id : Int = 0,
    val weight : Float,
    val height : Float,
    val skMuscle : Float?,
    val bodyFat : Float?,
    val BMI : Float?,
    val BMR : Float?)