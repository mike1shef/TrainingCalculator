package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingsDAO {

    @Upsert
    suspend fun addTraining(event: Event)

    @Upsert
    suspend fun addPayment(payment: Payment)

    @Upsert
    suspend fun addBodyMeasurements(bodyMeasurements: BodyMeasurements)

    @Delete
    suspend fun deleteTraining(event: Event)

    @Delete
    suspend fun deletePayment(payment: Payment)

    @Delete
    suspend fun deleteBodyMeasurements(bodyMeasurements: BodyMeasurements)

    @Query("SELECT * FROM Event")
    fun getAllTrainings() :Flow<List<Event>>

    @Query("SELECT * FROM Payment")
    fun getAllPayments() : Flow<List<Payment>>

    @Query("SELECT * FROM BodyMeasurements")
    fun getAllBodyMeasurements() : Flow<List<BodyMeasurements>>

}