package database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import utils.getCurrentDate

@Entity
data class BodyMeasurements (
    @PrimaryKey(autoGenerate = true)  val id : Int = 0,
    val skMuscle : String,
    val date: LocalDate,
    val bodyFat : String,
    val bmi : String,
    val bmr : String){

    constructor() : this(skMuscle = "", date = getCurrentDate(), bodyFat = "", bmi = "", bmr = "")
}

@Entity
data class Weight (
    @PrimaryKey(autoGenerate = true)  val id : Int = 0,
    val weight : String,
    val date: LocalDate ) {

    constructor() : this(id = 0, weight = "", date = getCurrentDate())
}