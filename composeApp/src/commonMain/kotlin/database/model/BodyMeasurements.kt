package database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import utils.getCurrentDate

@Entity
data class BodyMeasurements(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val skMuscle: String,
    val date: LocalDate,
    val bodyFat: String,
    val bmi: String,
    val bmr: String
) {

    constructor() : this(skMuscle = "", date = getCurrentDate(), bodyFat = "", bmi = "", bmr = "")
    fun toBodyMeasurementsForUI(): BodyMeasurementsForUI {
        return BodyMeasurementsForUI(this.date.toString(), this.skMuscle, " ", this.bodyFat, " ", this.bmi, " ", this.bmr, " ")
    }
}

data class BodyMeasurementsForUI(
    val date: String,
    val skMuscle: String,
    val skMuscleDifference: String,
    val bodyFat: String,
    val bodyFatDifference: String,
    val bmi: String,
    val bmiDifference: String,
    val bmr: String,
    val bmrDifference: String,
)

@Entity
data class Weight(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weight: String,
    val date: LocalDate
) {

    constructor() : this(id = 0, weight = "", date = getCurrentDate())

}

data class WeightForUI (
    val date: String,
    val weight: String,
    val difference: String
)