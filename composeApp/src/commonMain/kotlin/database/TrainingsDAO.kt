package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TrainingsDAO {

    @Upsert
    fun addTraining(event: Event)

    @Upsert
    fun addPayment(payment: Payment)

    @Upsert
    fun addBodyMeasurements(bodyMeasurements: BodyMeasurements)

    @Delete
    fun deleteTraining(event: Event)

    @Delete
    fun deletePayment(payment: Payment)

    @Delete
    fun deleteBodyMeasurements(bodyMeasurements: BodyMeasurements)

    @Query("SELECT * FROM Event")
    fun getAllTrainings() : List<Event>

    @Query("SELECT * FROM Payment")
    fun getAllPayments() : List<Payment>

    @Query("SELECT * FROM BodyMeasurements")
    fun getAllBodyMeasurements() : List<BodyMeasurements>

}