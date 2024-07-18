package database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity
data class BodyMeasurements (
    @PrimaryKey(autoGenerate = true)  val id : Int = 0,
    val skMuscle : String,
    val date: String,
    val bodyFat : String,
    val bmi : String,
    val bmr : String)

@Entity
data class Weight (
    @PrimaryKey(autoGenerate = true)  val id : Int = 0,
    val weight : String,
    val date: String, )